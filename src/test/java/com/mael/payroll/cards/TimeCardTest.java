package com.mael.payroll.cards;

import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static org.junit.Assert.*;

public class TimeCardTest {

    private LocalDate friday;
    private double hours;
    private PaymentSchedule weekly;

    @Before
    public void setUp() {
        friday = of(2016, JANUARY, 29);
        hours  = 8.0;
        weekly = new WeeklySchedule();
    }

    @Test
    public void createsATimeCard() {
        TimeCard timeCard = new TimeCard(friday, hours);
        assertEquals(friday, timeCard.getDate());
        assertEquals(hours,  timeCard.getHours(), 0.001);
    }

    @Test
    public void isInPeriodIfWeeklyScheduleAndInCurrentWeek() {
        LocalDate thursday = of(2016, JANUARY, 28);
        TimeCard timeCard  = new TimeCard(thursday, hours);
        assertTrue(timeCard.isInPeriod(friday, weekly));
    }

    @Test
    public void isNotInPeriodIfWeeklyScheduleAndInLastWeek() {
        LocalDate lastFriday = of(2016, JANUARY, 22);
        TimeCard timeCard    = new TimeCard(lastFriday, hours);
        assertFalse(timeCard.isInPeriod(friday, weekly));
    }

    @Test
    public void isNotInPeriodIfWeeklyScheduleAndInNextWeek() {
        LocalDate nextMonday = of(2016, FEBRUARY, 1);
        TimeCard timeCard    = new TimeCard(nextMonday, hours);
        assertFalse(timeCard.isInPeriod(friday, weekly));
    }
}
