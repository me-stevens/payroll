package com.mael.payroll.cards;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class TimeCardTest {
    @Test
    public void createsATimeCard() {
        LocalDate friday = of(2016, JANUARY, 29);
        TimeCard timeCard = new TimeCard(friday, 8.0);
        assertEquals(friday, timeCard.getDate());
        assertEquals(8.0, timeCard.getHours(), 0.001);
    }
}
