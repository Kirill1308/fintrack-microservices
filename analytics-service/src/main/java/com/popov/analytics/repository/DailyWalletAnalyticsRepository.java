package com.popov.analytics.repository;

import com.popov.analytics.entity.DailyWalletAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyWalletAnalyticsRepository extends JpaRepository<DailyWalletAnalytics, Long> {

    Optional<DailyWalletAnalytics> findByWalletIdAndDate(Long walletId, LocalDate date);

    List<DailyWalletAnalytics> findAllByWalletIdAndDateBetween(Long walletId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(dwa.totalIncome) FROM DailyWalletAnalytics dwa WHERE dwa.walletId = :walletId")
    Double getTotalIncomeForWallet(Long walletId);

    @Query("SELECT SUM(dwa.totalExpenses) FROM DailyWalletAnalytics dwa WHERE dwa.walletId = :walletId")
    Double getTotalExpensesForWallet(Long walletId);

}
