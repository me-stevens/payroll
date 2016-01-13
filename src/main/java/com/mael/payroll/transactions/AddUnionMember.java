package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.UnionAffiliation;

public class AddUnionMember {
    private final double fees;
    private int memberId;
    private int employeeId;
    private PayrollDBFacade payrollDB;

    public AddUnionMember(PayrollDBFacade payrollDB, int memberId, int employeeId, double fees) {
        this.payrollDB  = payrollDB;
        this.memberId   = memberId;
        this.employeeId = employeeId;
        this.fees       = fees;
    }

    public void execute() {
        payrollDB.addUnionMember(memberId, employeeId);
        payrollDB.getEmployee(employeeId).setAffiliation(new UnionAffiliation(fees));
    }
}
