package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollDBFacadeTest {

    @Test
    public void returnsNullIfEmployeeIdDoesntExist() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        assertEquals(null, payrollDB.getEmployee(1));
    }
}
