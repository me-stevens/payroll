package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddCommissionedEmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddCommissionedEmployee addCommissionedE = new AddCommissionedEmployee(payrollDB);

        addCommissionedE.execute(1, "Squiddo", "FishBowl", 1000.0);
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsCommissionedEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeASCommissioned() {
        assertTrue(employee.getPaymentType() instanceof CommissionedPayment);
    }

    @Test
    public void setsThePaymentScheduleAsCommissioned() {
        assertTrue(employee.getPaymentSchedule() instanceof CommissionedSchedule);
    }
}
