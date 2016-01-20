package com.mael.payroll.cards;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public abstract class Card {

    private LocalDate date;

    public Card(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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
