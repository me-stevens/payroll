package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;
import org.junit.Before;
import org.junit.Test;

public class DeleteEmployeeTest {

    private int employeeId;
    private int memberId;
    private PayrollDBFacade payrollDB;
    private AddEmployee addMonthlyEmployee;

    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
        payrollDB  = new PayrollDBFacade();
        addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();
    }

    @Test (expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void deletesEmployee() {
        DeleteEmployee deleteEmployee = new DeleteEmployee(payrollDB, employeeId);
        deleteEmployee.execute();

        payrollDB.getEmployee(employeeId);
    }

    @Test (expected = PayrollDBFacade.NotAUnionMemberException.class)
    public void ifEmployeeIsUnionMemberDeletesItsMemberId() {
        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId, 100.0);
        addUnionMember.execute();

        DeleteEmployee deleteEmployee = new DeleteEmployee(payrollDB, employeeId);
        deleteEmployee.execute();

        payrollDB.getUnionMember(memberId);
    }
}
