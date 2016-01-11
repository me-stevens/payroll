package com.mael.payroll.cards;

import java.time.LocalDate;

public class AffiliationCard {
    private LocalDate date;
    private double charges;

    public AffiliationCard(LocalDate date, double charges) {
        this.date    = date;
        this.charges = charges;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getCharges() {
        return charges;
    }
}
