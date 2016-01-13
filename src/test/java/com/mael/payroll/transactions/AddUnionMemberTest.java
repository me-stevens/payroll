package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.UnionAffiliation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddUnionMemberTest {

    private Employee employee;
    private int employeeId;
    private int memberId;
    private PayrollDBFacade payrollDB;
    private double fees;

    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
        fees       = 100.0;
        payrollDB  = new PayrollDBFacade();

        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        employee = payrollDB.getEmployee(employeeId);
    }

    @Test
    public void addsAMonthlyEmployeeToTheUnion() {
        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId, fees);
        addUnionMember.execute();
        assertEquals(employee, payrollDB.getUnionMember(memberId));
        assertTrue(employee.getAffiliation() instanceof UnionAffiliation);
    }
}
