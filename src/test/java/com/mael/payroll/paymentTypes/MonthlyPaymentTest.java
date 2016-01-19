package com.mael.payroll.paymentTypes;

import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class MonthlyPaymentTest {

    @Test
    public void calculatesPay() {
        MonthlyPayment monthlyPayment = new MonthlyPayment(1000.0);
        PaymentSchedule monthlySched  = new MonthlySchedule();
        assertEquals(1000.0, monthlyPayment.calculatePay(of(2016, JANUARY, 31),  monthlySched), 0.001);
    }
}
