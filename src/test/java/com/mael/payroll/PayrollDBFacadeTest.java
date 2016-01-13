package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollDBFacadeTest {

    private PayrollDBFacade payrollDB;
    private Employee employee;
    private int employeeId;

    @Before
    public void setUp() {
        employeeId = 1;
        payrollDB  = new PayrollDBFacade();
        employee   = new Employee("name", "address");
        payrollDB.addEmployee(employeeId, employee);
    }

    @Test
    public void addsAnEmployeeToTheDB() {
        assertEquals(employee, payrollDB.getEmployee(employeeId));
    }

    @Test(expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void throwsExceptionIfEmployeeNotInDB() {
        payrollDB.getEmployee(2);
    }

    @Test
    public void getsAllEmployees() {
        assertEquals(1, payrollDB.getAllEmployees().size());
    }

    @Test
    public void addsAUnionMember() {
        int memberId = 10;
        payrollDB.addUnionMember(memberId, employeeId);
        assertEquals(employee, payrollDB.getUnionMember(memberId));
    }

    @Test (expected = PayrollDBFacade.UnionMemberNotInDBException.class)
    public void throwsExceptionIfUnionMemberNotInDB() {
        payrollDB.getUnionMember(employeeId);
    }
}
