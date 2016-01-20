package com.mael.payroll.cards;

import java.time.LocalDate;

public class SalesCard extends Card {
    private double amount;

    public SalesCard(LocalDate date, double amount) {
        super(date);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
