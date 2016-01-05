package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollDBFacadeTest {

    @Test
    public void addsAnEmployeeToTheDB() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        Employee employee = new Employee("name", "address");
        payrollDB.addEmployee(1, employee);
        assertEquals(employee, payrollDB.getEmployee(1));
    }

    @Test(expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void throwExceptionIfEmployeeNotInDB() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        payrollDB.getEmployee(1);
    }
}
