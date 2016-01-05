package com.mael.payroll.cards;

public class SalesCard {
    private double amount;
    private int date;

    public SalesCard(int date, double amount) {
        this.date   = date;
        this.amount = amount;
    }

    public int getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
