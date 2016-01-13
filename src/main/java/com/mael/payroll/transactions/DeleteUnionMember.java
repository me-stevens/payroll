package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.NoAffiliation;

public class DeleteUnionMember {

    private PayrollDBFacade payrollDB;
    private int memberId;

    public DeleteUnionMember(PayrollDBFacade payrollDB, int memberId) {
        this.payrollDB = payrollDB;
        this.memberId  = memberId;
    }

    public void execute() {
        payrollDB.getUnionMember(memberId).setAffiliation(new NoAffiliation());
        payrollDB.deleteUnionMember(memberId);
    }
}
