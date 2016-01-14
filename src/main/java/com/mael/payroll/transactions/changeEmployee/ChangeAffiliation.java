package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.UnionAffiliation;

public class ChangeAffiliation {

    private PayrollDBFacade payrollDB;
    private int memberId;
    private int newMemberId;
    private double newFees;

    public ChangeAffiliation(PayrollDBFacade payrollDB, int memberId, int newMemberId, double newFees) {
        this.payrollDB   = payrollDB;
        this.memberId    = memberId;
        this.newMemberId = newMemberId;
        this.newFees     = newFees;
    }

    public void execute() {
        int employeeId = payrollDB.getUnionEmployeeId(memberId);
        payrollDB.deleteUnionMember(memberId);
        payrollDB.addUnionMember(newMemberId, employeeId);
        payrollDB.getEmployee(employeeId).setAffiliation(new UnionAffiliation(newFees));
    }
}
