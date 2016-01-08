package com.mael.payroll.paymentSchedules;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeeklyScheduleTest {

    @Test
    public void itIsPayDayIfItsFriday() {
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        LocalDate friday = of(2016, JANUARY, 29);
        assertTrue(weeklySchedule.isPayDay(friday));
    }

    @Test
    public void isNotPayDayIfItsNotFriday() {
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        LocalDate notAfriday = of(2016, JANUARY, 30);
        assertFalse(weeklySchedule.isPayDay(notAfriday));
    }
}
