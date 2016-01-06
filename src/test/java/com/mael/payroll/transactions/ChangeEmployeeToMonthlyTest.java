package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentTypes.MonthlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeEmployeeToMonthlyTest {
    
    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangeEmployeeToMonthly changeEmployeeToMonthly = new ChangeEmployeeToMonthly(payrollDB, 1, 2000.0);
        changeEmployeeToMonthly.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void changesEmployeeToMonthlySetsMonthlyPayment() {
        assertTrue(employee.getPaymentType() instanceof MonthlyPayment);
    }

    @Test
    public void changesEmployeeToMonthlySetsTheCorrectMonthlyRate() {
        MonthlyPayment monthlyPayment = (MonthlyPayment) employee.getPaymentType();
        assertEquals(2000.0, monthlyPayment.getMonthlyRate(), 0.001);
    }

    @Test
    public void changesEmployeeToMonthlySetsMonthlySchedule() {
        assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }
}
