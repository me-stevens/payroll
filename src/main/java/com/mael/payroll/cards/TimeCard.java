package com.mael.payroll.cards;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public class TimeCard {
    private LocalDate date;
    private double hours;

    public TimeCard(LocalDate date, double hours) {
        this.date  = date;
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }

    public boolean isInPeriod(LocalDate payDay, PaymentSchedule paySched) {
        return isAfter(payDay, paySched) && isBefore(payDay);
    }

    private boolean isAfter(LocalDate payDay, PaymentSchedule paySched) {
        return date.isAfter(payDay.minusDays(paySched.getDaysInPeriod()));
    }

    private boolean isBefore(LocalDate payDay) {
        return date.isBefore(payDay.plusDays(1));
    }
}
