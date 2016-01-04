package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddHourlyEmployeeTest {
    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB    = new PayrollDBFacade();
        AddHourlyEmployee addHourlyE = new AddHourlyEmployee(payrollDB);

        addHourlyE.execute(1, "Squiddo", "FishBowl", 1000.0);
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsHourlyEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeASHourly() {
        assertTrue(employee.getPaymentType() instanceof HourlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsHourly() {
        assertTrue(employee.getPaymentSchedule() instanceof HourlySchedule);
    }
}