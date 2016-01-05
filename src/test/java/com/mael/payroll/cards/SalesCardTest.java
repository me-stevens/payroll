package com.mael.payroll.cards;

import com.mael.payroll.cards.SalesCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesCardTest {
    @Test
    public void createsASalesCard() {
        SalesCard salesCard = new SalesCard(10001010, 100.0);
        assertEquals(10001010, salesCard.getDate());
        assertEquals(100.0, salesCard.getAmount(), 0.001);
    }
}
