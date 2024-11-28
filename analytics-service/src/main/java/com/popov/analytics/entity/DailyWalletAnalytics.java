package com.popov.analytics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "daily_wallet_analytics")
@Getter
@Setter
@NoArgsConstructor
public class DailyWalletAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long walletId;

    @Column(nullable = false)
    private LocalDate date;

    private Double currentWalletBalance = 0.0;
    private Double totalIncome = 0.0;
    private Double totalExpenses = 0.0;

    public DailyWalletAnalytics(Long walletId, LocalDate date) {
        this.walletId = walletId;
        this.date = date;
    }

    public void addIncome(Double amount) {
        totalIncome += amount;
        currentWalletBalance += amount;
    }

    public void addExpense(Double amount) {
        totalExpenses += amount;
        currentWalletBalance -= amount;
    }

}
