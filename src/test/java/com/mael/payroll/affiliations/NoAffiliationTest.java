package com.mael.payroll.affiliations;

import org.junit.Before;
import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class NoAffiliationTest {

    private Affiliation noAffiliation;

    @Before
    public void setUp() {
        noAffiliation = new NoAffiliation();
    }

    @Test
    public void feesAreZero() {
        assertEquals(0.0, noAffiliation.getFees(), 0.001);
    }

    @Test
    public void deductionsAreZero() {
        assertEquals(0.0, noAffiliation.calculateDeductions(of(2016, JANUARY, 31)), 0.001);
    }
}
