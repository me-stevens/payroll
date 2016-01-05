package com.mael.payroll.cards;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeCardTest {
    @Test
    public void createsATimeCard() {
        SalesCard salesCard = new SalesCard(10001010, 8.0);
        assertEquals(10001010, salesCard.getDate());
        assertEquals(8.0, salesCard.getAmount(), 0.001);
    }
}
