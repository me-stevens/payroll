package com.mael.payroll.cards;

import java.time.LocalDate;

public class AffiliationCard extends Card {
    private double charges;

    public AffiliationCard(LocalDate date, double charges) {
        super(date);
        this.charges = charges;
    }

    public double getCharges() {
        return charges;
    }
}
