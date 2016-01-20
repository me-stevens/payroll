package com.mael.payroll.paymentTypes;

import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class MonthlyPaymentTest {

    private MonthlyPayment monthlyPayment;
    private PaymentSchedule monthlySched;
    private LocalDate lastDayOfMonth;

    @Before
    public void setUp() {
        monthlyPayment = new MonthlyPayment(1000.0);
        monthlySched   = new MonthlySchedule();
        lastDayOfMonth = of(2016, JANUARY, 31);
    }

    @Test
    public void calculatesPay() {
        assertEquals(1000.0, monthlyPayment.calculatePay(lastDayOfMonth, monthlySched), 0.001);
    }

    @Test
    public void getsTheDaysWorked() {
        assertEquals(monthlySched.getDaysInPeriod(), monthlyPayment.getDaysWorked(lastDayOfMonth, monthlySched), 0.001);
    }
}
