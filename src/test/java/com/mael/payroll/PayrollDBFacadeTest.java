package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollDBFacadeTest {

    private PayrollDBFacade payrollDB;

    @Before
    public void setUp() {
        payrollDB = new PayrollDBFacade();
    }

    @Test
    public void addsAnEmployeeToTheDB() {
        Employee employee = new Employee("name", "address");
        payrollDB.addEmployee(1, employee);
        assertEquals(employee, payrollDB.getEmployee(1));
    }

    @Test(expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void throwsExceptionIfEmployeeNotInDB() {
        payrollDB.getEmployee(1);
    }

    @Test
    public void getsAllEmployees() {
        Employee employee = new Employee("name", "address");
        payrollDB.addEmployee(1, employee);
        assertEquals(1, payrollDB.getAllEmployees().size());
    }
}
