package com.mael.payroll.cards;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class TimeCardTest {

    private LocalDate friday;
    private double hours;

    @Before
    public void setUp() {
        friday = of(2016, JANUARY, 29);
        hours  = 8.0;
    }

    @Test
    public void createsATimeCard() {
        TimeCard timeCard = new TimeCard(friday, hours);
        assertEquals(friday, timeCard.getDate());
        assertEquals(hours, timeCard.getHours(), 0.001);
    }
}
