package com.mael.payroll.cards;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class AffiliationCardTest {

    private LocalDate friday;
    private double charges;

    @Before
    public void setUp() {
        friday  = of(2016, JANUARY, 29);
        charges = 100.0;
    }

    @Test
    public void createsAnAffiliationCard() {
        AffiliationCard affiliationCard = new AffiliationCard(friday, charges);
        assertEquals(friday, affiliationCard.getDate());
        assertEquals(charges, affiliationCard.getCharges(), 0.001);
    }
}
