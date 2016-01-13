package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddUnionMemberTest {

    private Employee employee;
    private int employeeId;
    private int memberId;
    private PayrollDBFacade payrollDB;

    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
        payrollDB  = new PayrollDBFacade();

        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        employee = payrollDB.getEmployee(employeeId);
    }

    @Test
    public void addsAMonthlyEmployeeToTheUnion() {
        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId);
        addUnionMember.execute();
        assertEquals(employee, payrollDB.getUnionMember(memberId));
    }
}
