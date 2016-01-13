package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.Affiliation;
import com.mael.payroll.affiliations.UnionAffiliation;
import com.mael.payroll.cards.AffiliationCard;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class AddAffiliationCardTest {

    private PayrollDBFacade payrollDB;
    private LocalDate monday;
    private Employee employee;
    private AddAffiliationCard addAffiliationCard;
    private double charges;
    private int employeeId;
    private int memberId;
    private double fees;

    @Before
    public void setUp() {
        employeeId = 1;
        memberId   = 10;
        fees       = 100.0;
        monday     = of(2016, JANUARY, 25);
        charges    = 50.0;
        payrollDB  = new PayrollDBFacade();

        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, employeeId, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();
        employee = payrollDB.getEmployee(employeeId);
    }

    @Test (expected = PayrollDBFacade.UnionMemberNotInDBException.class)
    public void throwsAnExceptionIfEmployeeHasNoAffiliation() {
        addAffiliationCard = new AddAffiliationCard(payrollDB, memberId, monday, charges);
        addAffiliationCard.execute();
    }

    @Test
    public void addsAnAffiliationCardToAMonthlyEmployee() {
        AddUnionMember addUnionMember = new AddUnionMember(payrollDB, memberId, employeeId, fees);
        addUnionMember.execute();

        addAffiliationCard = new AddAffiliationCard(payrollDB, memberId, monday, charges);
        addAffiliationCard.execute();

        UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
        AffiliationCard affiliationCard   = unionAffiliation.getAffiliationCard(monday);

        assertEquals(monday,  affiliationCard.getDate());
        assertEquals(charges, affiliationCard.getCharges(), 0.001);
    }
}
