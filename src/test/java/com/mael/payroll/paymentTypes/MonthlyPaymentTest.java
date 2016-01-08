package com.mael.payroll.paymentTypes;

import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class MonthlyPaymentTest {

    @Test
    public void calculatesPay() {
        MonthlyPayment monthlyPayment = new MonthlyPayment(1000.0);
        assertEquals(1000.0, monthlyPayment.calculatePay(of(2016, JANUARY, 31)), 0.001);
    }
}
