package com.mael.payroll.cards;

import java.time.LocalDate;

public class TimeCard extends Card {
    private double hours;

    public TimeCard(LocalDate date, double hours) {
        super(date);
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }
}
