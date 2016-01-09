package com.mael.payroll.cards;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class SalesCardTest {
    @Test
    public void createsASalesCard() {
        LocalDate monday = of(2016, JANUARY, 25);
        SalesCard salesCard = new SalesCard(monday, 100.0);
        assertEquals(monday, salesCard.getDate());
        assertEquals(100.0, salesCard.getAmount(), 0.001);
    }
}
