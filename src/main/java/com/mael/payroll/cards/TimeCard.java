package com.mael.payroll.cards;

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

    public boolean isInPeriod(LocalDate payDay) {
        int daysInPeriod = 5;
        return getDate().isAfter(payDay.minusDays(daysInPeriod));
    }
}
