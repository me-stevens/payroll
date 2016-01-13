package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;

public class AddUnionMember {
    private int memberId;
    private int employeeId;
    private PayrollDBFacade payrollDB;

    public AddUnionMember(PayrollDBFacade payrollDB, int memberId, int employeeId) {
        this.payrollDB  = payrollDB;
        this.memberId   = memberId;
        this.employeeId = employeeId;
    }

    public void execute() {
        payrollDB.addUnionMember(memberId, employeeId);
    }
}
