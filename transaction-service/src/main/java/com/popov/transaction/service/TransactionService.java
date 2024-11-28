package com.popov.transaction.service;

import com.popov.exception.custom.TransactionLimitReachedException;
import com.popov.exception.custom.TransactionNotFoundException;
import com.popov.kafka.event.TransactionEvent;
import com.popov.kafka.event.TransactionEventType;
import com.popov.kafka.event.WalletDeletedEvent;
import com.popov.kafka.producer.TransactionEventProducer;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.transaction.client.subscription.SubscriptionTierClient;
import com.popov.transaction.dto.TransactionFilter;
import com.popov.transaction.entity.Transaction;
import com.popov.transaction.repository.TransactionRepository;
import com.popov.transaction.utils.TransactionCriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final SubscriptionTierClient subscriptionTierClient;
    private final TransactionEventProducer transactionEventProducer;

    @Transactional(readOnly = true)
    public Page<Transaction> getFilteredTransactions(TransactionFilter filter, Pageable pageable) {
        Specification<Transaction> spec = TransactionCriteriaBuilder.buildCriteria(filter);
        Sort sort = TransactionCriteriaBuilder.buildSort(filter);

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return transactionRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        if (hasReachedMaxTransactions(transaction.getWalletId())) {
            throw new TransactionLimitReachedException();
        }

        transaction.setOwnerId(UserContextHolder.getUserContext().getUserId());

        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionEvent event = new TransactionEvent(
                transaction.getWalletId(),
                transaction.getAmount(),
                null,
                transaction.getType(),
                transaction.getDateCreated(),
                TransactionEventType.CREATE
        );

        transactionEventProducer.send(event);

        return savedTransaction;
    }

    @Transactional
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));

        BeanUtils.copyProperties(transaction, existingTransaction, "id");

        Transaction updatedTransaction = transactionRepository.save(existingTransaction);

        TransactionEvent event = new TransactionEvent(
                existingTransaction.getWalletId(),
                transaction.getAmount(),
                existingTransaction.getAmount(),
                existingTransaction.getType(),
                existingTransaction.getDateCreated(),
                TransactionEventType.UPDATE
        );

        transactionEventProducer.send(event);

        return updatedTransaction;
    }

    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        transactionRepository.delete(transaction);

        TransactionEvent event = new TransactionEvent(
                transaction.getWalletId(),
                transaction.getAmount(),
                null,
                transaction.getType(),
                transaction.getDateCreated(),
                TransactionEventType.DELETE
        );

        transactionEventProducer.send(event);
    }

    @Transactional
    @KafkaListener(topics = "wallet-deleted", groupId = "transaction-service")
    public void deleteAllByWalletId(WalletDeletedEvent event) {
        transactionRepository.deleteAllByWalletId(event.getWalletId());
    }

    private boolean hasReachedMaxTransactions(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        Integer currentTransactionsCount = transactionRepository.countByWalletId(walletId);
        if (currentTransactionsCount == 0) {
            return false;
        }

        var tier = subscriptionTierClient.getSubscriptionTier(userContext.getUserId());

        return tier != null && currentTransactionsCount >= tier.getMaxTransactions();
    }

}
