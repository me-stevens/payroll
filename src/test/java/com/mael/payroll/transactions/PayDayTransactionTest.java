package com.mael.payroll.transactions;

import com.mael.payroll.Paycheck;
import com.mael.payroll.PayrollDBFacade;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class PayDayTransactionTest {

    private PayrollDBFacade payrollDB;
    private int employeeId;

    @Before
    public void setUp() {
        payrollDB  = new PayrollDBFacade();
        employeeId = 1;
    }

    @Test
    public void paysMonthlyEmployeeAtTheEndOfTheMonth() {
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        LocalDate payDay = of(2016, JANUARY, 31);
        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, payDay);
        payDayTransaction.execute();

        Paycheck paycheck = payDayTransaction.getPayChecks().get(employeeId);
        assertEquals(payDay, paycheck.getDate());
        assertEquals(1000.0, paycheck.getNetPay(), 0.001);
    }

    @Test
    public void doesntPayMonthlyEmployeeIfNotAtEndOfMonth() {
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        LocalDate payDay = of(2016, JANUARY, 30);
        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, payDay);
        payDayTransaction.execute();

        assertEquals(null, payDayTransaction.getPayChecks().get(employeeId));
    }

    @Test
    public void paysHourlyEmployeeWithNoTimeCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        LocalDate friday = of(2016, JANUARY, 29);
        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, friday);
        payDayTransaction.execute();

        Paycheck paycheck = payDayTransaction.getPayChecks().get(employeeId);
        assertEquals(friday, paycheck.getDate());
        assertEquals(0.0, paycheck.getNetPay(), 0.001);
    }
}
