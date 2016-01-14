package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;

public class DeleteEmployee {

    private PayrollDBFacade payrollDB;
    private int employeeId;

    public DeleteEmployee(PayrollDBFacade payrollDB, int employeeId) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
    }

    public void execute() {
        deleteMembershipIfAny();
        payrollDB.deleteEmployee(employeeId);
    }

    private void deleteMembershipIfAny() {
        try {
            int memberId = payrollDB.getUnionMemberId(employeeId);
            payrollDB.deleteUnionMember(memberId);
        } catch (PayrollDBFacade.NotAUnionMemberException e) {
        }
    }
}
