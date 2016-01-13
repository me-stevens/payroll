package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.UnionAffiliation;
import com.mael.payroll.cards.AffiliationCard;

import java.time.LocalDate;

public class AddAffiliationCard {
    private final PayrollDBFacade payrollDB;
    private final int memberId;
    private final LocalDate date;
    private final double charges;

    public AddAffiliationCard(PayrollDBFacade payrollDB, int memberId, LocalDate date, double charges) {
        this.payrollDB = payrollDB;
        this.memberId  = memberId;
        this.date      = date;
        this.charges   = charges;
    }

    public void execute() {
        Employee employee = payrollDB.getUnionMember(memberId);
        ((UnionAffiliation) employee.getAffiliation()).addAffiliationCard(new AffiliationCard(date, charges));
    }
}
