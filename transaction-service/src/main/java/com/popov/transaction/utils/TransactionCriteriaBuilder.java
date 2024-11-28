package com.popov.transaction.utils;

import com.popov.transaction.dto.TransactionFilter;
import com.popov.transaction.entity.Transaction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionCriteriaBuilder {

    @Getter
    @AllArgsConstructor
    private enum TransactionField {
        WALLET_ID("walletId"),
        OWNER_ID("ownerId"),
        TYPE("type"),
        CATEGORY("category"),
        CATEGORY_NAME("name"),
        PAYMENT("payment"),
        AMOUNT("amount"),
        DATE_CREATED("dateCreated");

        private final String fieldName;
    }

    public static Specification<Transaction> buildCriteria(TransactionFilter filter) {
        return Specification.where(walletEquals(filter.getWalletId()))
                .and(userEquals(filter.getUserIds()))
                .and(transactionTypesIn(filter.getTransactionTypes()))
                .and(categoriesIn(filter.getCategories()))
                .and(paymentMethodsIn(filter.getPaymentMethods()))
                .and(amountGreaterThan(filter.getMinAmount()))
                .and(amountLessThan(filter.getMaxAmount()))
                .and(dateAfter(filter.getStartDate()))
                .and(dateBefore(filter.getEndDate()));
    }

    public static Sort buildSort(TransactionFilter filter) {
        String orderBy = filter.getOrderBy();
        String direction = filter.getOrderDirection();

        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = TransactionField.DATE_CREATED.getFieldName();
        }

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return Sort.by(sortDirection, orderBy);
    }

    private static Specification<Transaction> walletEquals(Long walletId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("walletId"), walletId);
    }

    private static Specification<Transaction> userEquals(List<Long> userIds) {
        return (root, query, criteriaBuilder) -> {
            if (userIds == null || userIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get(TransactionField.OWNER_ID.getFieldName()).in(userIds);
        };
    }

    private static Specification<Transaction> transactionTypesIn(List<String> transactionTypes) {
        return (root, query, criteriaBuilder) -> {
            if (transactionTypes == null || transactionTypes.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get(TransactionField.TYPE.getFieldName()).in(transactionTypes);
        };
    }

    private static Specification<Transaction> categoriesIn(List<String> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get(TransactionField.CATEGORY.getFieldName())
                    .get(TransactionField.CATEGORY_NAME.getFieldName())
                    .in(categories);
        };
    }

    private static Specification<Transaction> paymentMethodsIn(List<String> paymentMethods) {
        return (root, query, criteriaBuilder) -> {
            if (paymentMethods == null || paymentMethods.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get(TransactionField.PAYMENT.getFieldName()).in(paymentMethods);
        };
    }

    private static Specification<Transaction> amountGreaterThan(Double minAmount) {
        return (root, query, criteriaBuilder) -> {
            if (minAmount == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(TransactionField.AMOUNT.getFieldName()), minAmount
            );
        };
    }

    private static Specification<Transaction> amountLessThan(Double maxAmount) {
        return (root, query, criteriaBuilder) -> {
            if (maxAmount == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(TransactionField.AMOUNT.getFieldName()), maxAmount
            );
        };
    }

    private static Specification<Transaction> dateAfter(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(TransactionField.DATE_CREATED.getFieldName()), startDate
            );
        };
    }

    private static Specification<Transaction> dateBefore(LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (endDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(TransactionField.DATE_CREATED.getFieldName()), endDate
            );
        };
    }

}
