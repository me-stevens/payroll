package com.mael.payroll.cards;

import com.mael.payroll.paymentSchedules.BiweeklySchedule;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static org.junit.Assert.*;

public class SalesCardTest {

    private double amount;
    private PaymentSchedule biweekly;
    private LocalDate friday;

    @Before
    public void setUp() {
        friday   = of(2016, JANUARY, 29);
        amount   = 100.0;
        biweekly = new BiweeklySchedule();
    }

    @Test
    public void createsASalesCard() {
        SalesCard salesCard = new SalesCard(friday, amount);
        assertEquals(friday, salesCard.getDate());
        assertEquals(amount, salesCard.getAmount(), 0.001);
    }

    @Test
    public void isInPeriodIfBiweeklyScheduleAndInCurrentBiweek() {
        LocalDate fridayTwoWeeksBack = of(2016, JANUARY, 15);
        SalesCard salesCard          = new SalesCard(fridayTwoWeeksBack, amount);
        assertTrue(salesCard.isInPeriod(friday, biweekly));
    }

    @Test
    public void isNotInPeriodIfBiWeeklyScheduleAndThreeWeeksBack() {
        LocalDate fridayThreeWeeksBack = of(2016, JANUARY, 8);
        SalesCard salesCard            = new SalesCard(fridayThreeWeeksBack, amount);
        assertFalse(salesCard.isInPeriod(friday, biweekly));
    }

    @Test
    public void isNotInperiodIfBiweeklyScheduleAndInNextWeek() {
        LocalDate nextMonday = of(2016, FEBRUARY, 1);
        SalesCard salesCard  = new SalesCard(nextMonday, amount);
        assertFalse(salesCard.isInPeriod(friday, biweekly));
    }
}
