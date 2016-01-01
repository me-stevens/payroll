package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddMonthlyEmployeeTest {

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyE = new AddMonthlyEmployee(payrollDB);
        addMonthlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertEquals("Squiddo", employee.getName());
    }
}
