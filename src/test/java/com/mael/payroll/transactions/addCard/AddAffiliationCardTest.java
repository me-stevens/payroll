package com.mael.payroll.transactions.addCard;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.affiliations.UnionAffiliation;
import com.mael.payroll.cards.AffiliationCard;
import com.mael.payroll.transactions.AddUnionMember;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

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
        List<AffiliationCard> affiliationCards = unionAffiliation.getAllCards();
        assertEquals(1,       affiliationCards.size());
        assertEquals(monday,  affiliationCards.get(0).getDate());
        assertEquals(charges, affiliationCards.get(0).getCharges(), 0.001);
    }
}
