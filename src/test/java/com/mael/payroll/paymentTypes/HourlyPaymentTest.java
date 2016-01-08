package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class HourlyPaymentTest {

    private HourlyPayment hourlyPayment;
    private LocalDate friday;

    @Before
    public void setUp() {
        hourlyPayment = new HourlyPayment(1000.0);
        friday = of(2016, JANUARY, 29);
    }

    @Test
    public void thePayIsZeroIfNoTimeCard() {
        assertEquals(0.0, hourlyPayment.calculatePay(friday), 0.001);
    }

    @Test
    public void thePayIsHoursByRateIfOneTimeCard() {
        hourlyPayment.addTimeCard(new TimeCard(friday, 8.0));
        assertEquals(8.0 * 1000.0, hourlyPayment.calculatePay(friday), 0.001);
    }

    @Test
    public void thePayIsTheSumOfTotalHoursByRateIfTwoTimeCards() {
        LocalDate monday = of(2016, JANUARY, 25);
        hourlyPayment.addTimeCard(new TimeCard(monday, 8.0));

        LocalDate tuesday = of(2016, JANUARY, 26);
        hourlyPayment.addTimeCard(new TimeCard(tuesday, 8.0));

        assertEquals(8.0 * (1000.0 + 1000.0), hourlyPayment.calculatePay(friday), 0.001);
    }

    @Test
    public void thePayIsCalculatedWithTheTimeCardsOfThePeriodOnly() {
        LocalDate lastFriday = of(2016, JANUARY, 22);
        hourlyPayment.addTimeCard(new TimeCard(lastFriday, 8.0));

        LocalDate monday = of(2016, JANUARY, 25);
        hourlyPayment.addTimeCard(new TimeCard(monday, 8.0));

        assertEquals(8.0 * 1000.0, hourlyPayment.calculatePay(friday), 0.001);
    }

    @Test (expected = HourlyPayment.TimeCardNotFoundException.class)
    public void throwsExceptionIfNoTimeCardIsFoundForADate() {
        hourlyPayment.getTimeCard(friday);
    }
}
