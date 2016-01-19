package com.mael.payroll.cards;

import java.time.LocalDate;

public class SalesCard {
    private LocalDate date;
    private double amount;

    public SalesCard(LocalDate date, double amount) {
        this.date   = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isInPeriod(LocalDate payDay) {
        int daysInPeriod = 15;
        return getDate().isAfter(payDay.minusDays(daysInPeriod));
    }
}
