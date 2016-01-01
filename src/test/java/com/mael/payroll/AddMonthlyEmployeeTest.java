package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddMonthlyEmployeeTest {

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        PayrollDB payrollDB = new PayrollDB();
        assertTrue(payrollDB.getEmployee(1) instanceof Employee);
    }
}
