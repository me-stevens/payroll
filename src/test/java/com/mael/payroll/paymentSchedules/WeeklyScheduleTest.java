package com.mael.payroll.paymentSchedules;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeeklyScheduleTest {

    private PaymentSchedule weeklySchedule;

    @Before
    public void setUp() throws Exception {
        weeklySchedule = new WeeklySchedule();
    }

    @Test
    public void itIsPayDayIfItIsFriday() {
        LocalDate friday = of(2016, JANUARY, 29);
        assertTrue(weeklySchedule.isPayDay(friday));
    }

    @Test
    public void itIsNotPayDayIfItIsNotFriday() {
        LocalDate notAfriday = of(2016, JANUARY, 30);
        assertFalse(weeklySchedule.isPayDay(notAfriday));
    }

    @Test
    public void getsDaysInPeriod() {
        assertEquals(5, weeklySchedule.getDaysInPeriod());
    }
}
