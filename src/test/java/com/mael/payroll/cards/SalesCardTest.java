package com.mael.payroll.cards;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class SalesCardTest {

    private LocalDate monday;
    private double amount;

    @Before
    public void setUp() {
        monday = of(2016, JANUARY, 25);
        amount = 100.0;
    }

    @Test
    public void createsASalesCard() {
        SalesCard salesCard = new SalesCard(monday, amount);
        assertEquals(monday, salesCard.getDate());
        assertEquals(amount, salesCard.getAmount(), 0.001);
    }
}
