package com.mael.payroll.cards;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

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

    public boolean isInPeriod(LocalDate payDay, PaymentSchedule paySched) {
        return isAfter(payDay, paySched) && isBefore(payDay);
    }

    private boolean isAfter(LocalDate payDay, PaymentSchedule paySched) {
        return getDate().isAfter(payDay.minusDays(paySched.getDaysInPeriod()));
    }

    private boolean isBefore(LocalDate payDay) {
        return date.isBefore(payDay.plusDays(1));
    }
}
