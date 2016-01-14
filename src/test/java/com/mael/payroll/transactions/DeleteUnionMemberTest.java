package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.NoAffiliation;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DeleteUnionMemberTest {

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

        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId, 100.0);
        addUnionMember.execute();

        DeleteUnionMember deleteUnionMember = new DeleteUnionMember(payrollDB, memberId);
        deleteUnionMember.execute();
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void deletesUnionMemberThatIsAMonthlyEmployee() {
        payrollDB.getUnionMember(memberId);
    }

    @Test
    public void deletingUnionMemberSetsAffiliationToNone() {
        Employee employee = payrollDB.getEmployee(employeeId);
        assertTrue(employee.getAffiliation() instanceof NoAffiliation);
    }
}
