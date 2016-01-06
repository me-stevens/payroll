package com.mael.payroll.cards;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeCardTest {
    @Test
    public void createsATimeCard() {
        TimeCard timeCard = new TimeCard(10001010, 8.0);
        assertEquals(10001010, timeCard.getDate());
        assertEquals(8.0, timeCard.getHours(), 0.001);
    }
}
