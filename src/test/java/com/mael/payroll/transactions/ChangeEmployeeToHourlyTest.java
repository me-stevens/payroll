package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeEmployeeToHourlyTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        ChangeEmployeeToHourly changeEmployeeToHourly = new ChangeEmployeeToHourly(payrollDB, 1, 2000.0);
        changeEmployeeToHourly.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void changesEmployeeToHourlySetsHourlyPayment() {
        assertTrue(employee.getPaymentType() instanceof HourlyPayment);
    }

    @Test
    public void changesEmployeeToHourlySetsWeeklySchedule() {
        assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
    }

    @Test
    public void changesEmployeeToHourlySetsTheCorrectHourlyRate() {
        HourlyPayment hourlyPayment = (HourlyPayment) employee.getPaymentType();
        assertEquals(2000.0, hourlyPayment.getHourlyRate(), 0.001);
    }
}
