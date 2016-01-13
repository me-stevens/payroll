package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeAffiliationTest {

    private PayrollDBFacade payrollDB;
    private Employee employee;
    private int employeeId;
    private int memberId;
    private double fees;


    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
        fees       = 100.0;
        payrollDB  = new PayrollDBFacade();
    }

    @Test
    public void changesAnAffiliationToAMonthlyEmployee() {
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();
        employee = payrollDB.getEmployee(employeeId);

        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId, fees);
        addUnionMember.execute();

        int newMemberId = 20;
        double newFees  = 200.0;
        ChangeAffiliation changeAffiliation = new ChangeAffiliation(payrollDB, memberId, newMemberId, newFees);
        changeAffiliation.execute();

        assertEquals(employee, payrollDB.getUnionMember(newMemberId));
        assertEquals(newFees, employee.getAffiliation().getFees(), 0.001);
    }
}
