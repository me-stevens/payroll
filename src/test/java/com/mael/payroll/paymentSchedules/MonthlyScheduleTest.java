package com.mael.payroll.paymentSchedules;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MonthlyScheduleTest {

    private PaymentSchedule monthlySchedule;

    @Before
    public void setUp() {
        monthlySchedule = new MonthlySchedule();
    }

    @Test
    public void itIsPayDayIfLastDayOfMonth() {
        LocalDate lastDayOfMonth = LocalDate.of(2016, JANUARY, 31);
        assertTrue(monthlySchedule.isPayDay(lastDayOfMonth));
    }

    @Test
    public void itIsNotPayDayIfNotLastDayOfMonth() {
        LocalDate notLastDayOfMonth = LocalDate.of(2016, JANUARY, 30);
        assertFalse(monthlySchedule.isPayDay(notLastDayOfMonth));
    }

    @Test
    public void getsTheDaysInThePeriod() {
        assertEquals(30, monthlySchedule.getDaysInPeriod());
    }
}
