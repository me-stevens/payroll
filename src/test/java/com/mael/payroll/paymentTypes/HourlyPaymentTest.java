package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static org.junit.Assert.assertEquals;

public class HourlyPaymentTest {

    private HourlyPayment hourlyPayment;

    @Before
    public void setUp() {
        hourlyPayment = new HourlyPayment(1000.0);
    }

    @Test
    public void thePayIsZeroIfNoTimeCard() {
        assertEquals(0.0, hourlyPayment.calculatePay(), 0.001);
    }

    @Test
    public void thePayIsHoursByRateIfOneTimeCard() {
        LocalDate friday = of(2016, Month.JANUARY, 29);
        hourlyPayment.addTimeCard(new TimeCard(friday, 8.0));
        assertEquals(8.0 * 1000.0, hourlyPayment.calculatePay(), 0.001);
    }

    @Test
    public void thePayIsTheSumOfTotalHoursByRateIfTwoTimeCards() {
        LocalDate lastFriday = of(2016, Month.JANUARY, 22);
        hourlyPayment.addTimeCard(new TimeCard(lastFriday, 8.0));

        LocalDate thisFriday = of(2016, Month.JANUARY, 29);
        hourlyPayment.addTimeCard(new TimeCard(thisFriday, 8.0));

        assertEquals(8.0 * (1000.0 + 1000.0), hourlyPayment.calculatePay(), 0.001);
    }

    @Test (expected = HourlyPayment.TimeCardNotFoundException.class)
    public void throwsExceptionIfNoTimeCardIsFoundForADate() {
        LocalDate friday = of(2016, Month.JANUARY, 29);
        hourlyPayment.getTimeCard(friday);
    }
}
