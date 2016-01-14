package com.mael.payroll.affiliations;

import com.mael.payroll.cards.AffiliationCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {

    private double fees;
    private List<AffiliationCard> affiliationCards;

    public UnionAffiliation(double fees) {
        this.fees             = fees;
        this.affiliationCards = new ArrayList<>();
    }

    @Override
    public double getFees() {
        return fees;
    }

    @Override
    public double calculateDeductions(LocalDate payDay) {
        return getFees() + getCharges(payDay);
    }

    public void addAffiliationCard(AffiliationCard affiliationCard) {
        this.affiliationCards.add(affiliationCard);
    }

    public List<AffiliationCard> getAllCards() {
        return affiliationCards;
    }

    private double getCharges(LocalDate payDay) {
        double charges = 0.0;
        for (AffiliationCard affiliationCard : affiliationCards) {
            if (isInPeriod(payDay, affiliationCard)) {
                charges += affiliationCard.getCharges();
            }
        }
        return charges;
    }

    private boolean isInPeriod(LocalDate payDay, AffiliationCard affiliationCard) {
        return affiliationCard.getDate().isAfter(payDay.minusDays(5));
    }
}
