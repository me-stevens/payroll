package com.mael.payroll.paymentSchedules;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class MonthlyScheduleTest {

    private MonthlySchedule monthlySchedule;

    @Before
    public void setUp() {
        monthlySchedule = new MonthlySchedule();
    }

    @Test
    public void returnsTrueIfLastDayOfMonth() {
        LocalDate lastDayOfMonth = LocalDate.of(2016, JANUARY, 31);
        assertTrue(monthlySchedule.isPayDay(lastDayOfMonth));
    }

    @Test
    public void returnsFalseIfNotLastDayOfMonth() {
        LocalDate notLastDayOfMonth = LocalDate.of(2016, JANUARY, 30);
        assertFalse(monthlySchedule.isPayDay(notLastDayOfMonth));
    }
}
