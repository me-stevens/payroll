package com.mael.payroll.transactions;

import com.mael.payroll.Paycheck;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.transactions.addCard.AddTimeCard;
import com.mael.payroll.transactions.addEmployee.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class PayDayTransactionAcceptanceTestsTest {

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
        LocalDate lastDayOfMonth = of(2016, JANUARY, 31);
        assertWasPaid(lastDayOfMonth, 1000.0);
    }

    @Test
    public void doesntPayMonthlyEmployeeIfNotAtEndOfMonth() {
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        LocalDate notTheLastDayOfMonth = of(2016, JANUARY, 30);

        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, notTheLastDayOfMonth);
        payDayTransaction.execute();

        assertEquals(null, payDayTransaction.getPayChecks().get(employeeId));
    }

    @Test
    public void paysZeroToHourlyEmployeeWithNoTimeCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();
        LocalDate friday = of(2016, JANUARY, 29);
        assertWasPaid(friday, 0.0);
    }

    @Test
    public void paysHoursByRateToHourlyEmployeeWithOneTimeCard() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        LocalDate friday = of(2016, JANUARY, 29);

        AddTimeCard addTimeCard = new AddTimeCard(payrollDB, employeeId, friday, 8.0);
        addTimeCard.execute();

        assertWasPaid(friday, 8.0 * 1000.0);
    }

    @Test
    public void paysHoursByRateToHourlyEmployeeWithTwoTimeCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        LocalDate monday = of(2016, JANUARY, 25);
        AddTimeCard addTimeCard = new AddTimeCard(payrollDB, employeeId, monday, 8.0);
        addTimeCard.execute();

        LocalDate tuesday = of(2016, JANUARY, 26);
        addTimeCard = new AddTimeCard(payrollDB, employeeId, tuesday, 8.0);
        addTimeCard.execute();

        LocalDate friday = of(2016, JANUARY, 29);
        assertWasPaid(friday, 8.0 * (1000.0 + 1000.0));
    }

    @Test
    public void doesntPayHourlyEmployeeIfItIsNotAFriday() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        LocalDate monday = of(2016, JANUARY, 25);
        AddTimeCard addTimeCard = new AddTimeCard(payrollDB, employeeId, monday, 8.0);
        addTimeCard.execute();

        LocalDate saturday = of(2016, JANUARY, 30);
        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, saturday);
        payDayTransaction.execute();

        assertEquals(null, payDayTransaction.getPayChecks().get(employeeId));
    }

    private void assertWasPaid(LocalDate payDay, double payAmount) {
        PayDayTransaction payDayTransaction = new PayDayTransaction(payrollDB, payDay);
        payDayTransaction.execute();

        Paycheck paycheck = payDayTransaction.getPayChecks().get(employeeId);

        assertEquals(payDay,    paycheck.getDate());
        assertEquals(payAmount, paycheck.getNetPay(), 0.001);
    }
}
