package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollDBFacadeTest {

    private PayrollDBFacade payrollDB;
    private Employee employee;
    private int employeeId;
    private int memberId;

    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
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

    @Test (expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void deletesEmployee() {
        payrollDB.deleteEmployee(employeeId);
        payrollDB.getEmployee(employeeId);
    }

    @Test (expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void throwsExceptionIfEmployeeToBeDeletedDoesntExist() {
        payrollDB.deleteEmployee(2);
    }

    @Test
    public void getsAllEmployees() {
        assertEquals(1, payrollDB.getAllEmployees().size());
    }

    @Test
    public void addsAUnionMember() {
        payrollDB.addUnionMember(memberId, employeeId);
        assertEquals(employee, payrollDB.getUnionMember(memberId));
    }

    @Test
    public void getsUnionMemberEmployeeId() {
        payrollDB.addUnionMember(memberId, employeeId);
        assertEquals(employeeId, payrollDB.getUnionEmployeeId(memberId));
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void throwsExceptionIfUnionMemberNotInDB() {
        payrollDB.getUnionMember(memberId);
        payrollDB.getUnionEmployeeId(memberId);
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void deletesAUnionMember() {
        payrollDB.addUnionMember(memberId, employeeId);
        payrollDB.deleteUnionMember(memberId);
        payrollDB.getUnionMember(memberId);
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void throwsAnExceptionIfUnionMemberToBeDeletedDoesntExist() {
        payrollDB.deleteUnionMember(memberId);
    }

    @Test
    public void givenAnEmployeeIdReturnsTheMemberId() {
        payrollDB.addUnionMember(10, 1);
        assertEquals(10, payrollDB.getUnionMemberId(employeeId));
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void throwsAnExceptionIfNoMemberIdIsFoundForAnEmployeeId() {
        payrollDB.getUnionMemberId(memberId);
    }
}
