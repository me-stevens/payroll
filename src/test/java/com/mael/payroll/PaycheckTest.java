package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class PaycheckTest {

    private LocalDate payday;
    private Paycheck paycheck;

    @Before
    public void setUp() {
        payday   = of(2016, JANUARY, 31);
        paycheck = new Paycheck(payday);
    }

    @Test
    public void getsTheDate() {
        assertEquals(payday, paycheck.getDate());
    }

    @Test
    public void setsTheNetPay() {
        paycheck.setNetPay(1000.0);
        assertEquals(1000.0, paycheck.getNetPay(), 0.001);
    }
}
