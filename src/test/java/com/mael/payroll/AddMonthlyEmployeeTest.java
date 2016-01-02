package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddMonthlyEmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB      = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyE = new AddMonthlyEmployee(payrollDB);

        addMonthlyE.execute(1, "Squiddo", "FishBowl", 1000.0);
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeAsMonthly() {
        assertTrue(employee.getPaymentType() instanceof MonthlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsMonthly() {
        assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}
