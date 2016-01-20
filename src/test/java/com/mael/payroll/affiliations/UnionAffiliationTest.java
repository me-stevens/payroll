package com.mael.payroll.affiliations;

import com.mael.payroll.cards.AffiliationCard;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class UnionAffiliationTest {

    private double fees;
    private UnionAffiliation unionAffiliation;
    private LocalDate friday;
    private double charges;
    private WeeklySchedule weekly;

    @Before
    public void setUp() {
        fees             = 100.0;
        unionAffiliation = new UnionAffiliation(fees);
        weekly           = new WeeklySchedule();
        friday           = of(2016, JANUARY, 29);
        charges          = 50.0;
    }

    @Test
    public void deductionsAreTheFeeIfNoAffiliationCard() {
        assertDeductions(fees);
    }

    @Test
    public void deductionsAreFeesPlusExtraChargesIfOneAffiliationCard() {
        unionAffiliation.addAffiliationCard(new AffiliationCard(friday, charges));
        assertDeductions(fees + charges);
    }

    @Test
    public void deductionsAreTheSumOfFeesAndAllChargesIfSeveralAffiliationCards() {
        LocalDate thursday = of(2016, JANUARY, 28);
        unionAffiliation.addAffiliationCard(new AffiliationCard(thursday, charges));
        unionAffiliation.addAffiliationCard(new AffiliationCard(friday, charges));
        assertDeductions(fees + charges + charges);
    }

    @Test
    public void onlyTheCardsInThePeriodAreUsed() {
        LocalDate lastFriday = of(2016, JANUARY, 22);
        unionAffiliation.addAffiliationCard(new AffiliationCard(lastFriday, charges));
        unionAffiliation.addAffiliationCard(new AffiliationCard(friday, charges));
        assertDeductions(fees + charges);
    }

    private void assertDeductions(double deductions) {
        assertEquals(deductions, unionAffiliation.calculateDeductions(friday, weekly), 0.001);
    }
}
