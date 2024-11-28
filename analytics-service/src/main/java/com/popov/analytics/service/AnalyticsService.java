package com.popov.analytics.service;

import com.popov.analytics.dto.WalletAnalyticsResponse;
import com.popov.analytics.entity.DailyWalletAnalytics;
import com.popov.analytics.repository.DailyWalletAnalyticsRepository;
import com.popov.exception.custom.InvalidEventTypeException;
import com.popov.exception.custom.InvalidTransactionTypeException;
import com.popov.kafka.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final DailyWalletAnalyticsRepository dailyAnalyticsRepository;

    @Transactional(readOnly = true)
    public WalletAnalyticsResponse getAnalyticsForPeriod(Long walletId, LocalDate periodStart, LocalDate periodEnd) {
        List<DailyWalletAnalytics> analyticsList = dailyAnalyticsRepository
                .findAllByWalletIdAndDateBetween(walletId, periodStart, periodEnd);

        Double totalPeriodIncome = analyticsList.stream().mapToDouble(DailyWalletAnalytics::getTotalIncome).sum();
        Double totalPeriodExpenses = analyticsList.stream().mapToDouble(DailyWalletAnalytics::getTotalExpenses).sum();
        Double totalPeriodChange = totalPeriodIncome - totalPeriodExpenses;

        Double currentWalletBalance = calculateCurrentWalletBalance(walletId);

        return new WalletAnalyticsResponse(currentWalletBalance, totalPeriodIncome, totalPeriodExpenses, totalPeriodChange);
    }

    @Transactional
    @KafkaListener(topics = "transaction-activity", groupId = "analytics-service")
    public void handleTransactionEvent(TransactionEvent event) {
        updateAnalyticsData(event);
    }

    private void updateAnalyticsData(TransactionEvent event) {
        DailyWalletAnalytics dailyAnalytics = dailyAnalyticsRepository
                .findByWalletIdAndDate(event.getWalletId(), event.getDate())
                .orElseGet(() -> new DailyWalletAnalytics(event.getWalletId(), event.getDate()));

        switch (event.getEventType()) {
            case CREATE -> processCreateEvent(dailyAnalytics, event);
            case UPDATE -> processUpdateEvent(dailyAnalytics, event);
            case DELETE -> processDeleteEvent(dailyAnalytics, event);
            default -> throw new InvalidEventTypeException(event.getEventType());
        }

        dailyAnalyticsRepository.save(dailyAnalytics);
    }

    private void processCreateEvent(DailyWalletAnalytics analyticsData, TransactionEvent event) {
        switch (event.getTransactionType()) {
            case INCOME -> analyticsData.addIncome(event.getAmount());
            case EXPENSE -> analyticsData.addExpense(event.getAmount());
            default -> throw new InvalidTransactionTypeException(event.getTransactionType());
        }
    }

    private void processUpdateEvent(DailyWalletAnalytics analyticsData, TransactionEvent event) {
        Double previousAmount = event.getPreviousAmount();
        Double amountDifference = event.getAmount() - previousAmount;

        switch (event.getTransactionType()) {
            case INCOME -> analyticsData.addIncome(amountDifference);
            case EXPENSE -> analyticsData.addExpense(amountDifference);
            default -> throw new InvalidTransactionTypeException(event.getTransactionType());
        }
    }

    private void processDeleteEvent(DailyWalletAnalytics analyticsData, TransactionEvent event) {
        switch (event.getTransactionType()) {
            case INCOME -> analyticsData.addIncome(-event.getAmount());
            case EXPENSE -> analyticsData.addExpense(-event.getAmount());
            default -> throw new InvalidTransactionTypeException(event.getTransactionType());
        }
    }

    private Double calculateCurrentWalletBalance(Long walletId) {
        Double walletTotalIncome = dailyAnalyticsRepository.getTotalIncomeForWallet(walletId);
        Double walletTotalExpenses = dailyAnalyticsRepository.getTotalExpensesForWallet(walletId);

        return walletTotalIncome - walletTotalExpenses;
    }

}
