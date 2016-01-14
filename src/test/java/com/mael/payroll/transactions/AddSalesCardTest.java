package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class AddSalesCardTest {

    private PayrollDBFacade payrollDB;
    private AddSalesCard addSalesCard;
    private LocalDate monday;

    @Before
    public void setUp() {
        payrollDB = new PayrollDBFacade();
        monday    = of(2016, JANUARY, 25);
    }

    @Test
    public void addsSalesCardToCommissionedEmployee() {
        AddEmployee addCommissionedEmployee = new AddCommissionedEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0, 2.0);
        addCommissionedEmployee.execute();
        Employee employee = payrollDB.getEmployee(1);

        addSalesCard = new AddSalesCard(payrollDB, 1, monday, 100.0);
        addSalesCard.execute();

        List<SalesCard> salesCards = ((CommissionedPayment) employee.getPaymentType()).getAllCards();
        assertEquals(1,      salesCards.size());
        assertEquals(monday, salesCards.get(0).getDate());
        assertEquals(100.0,  salesCards.get(0).getAmount(), 0.001);
    }

    @Test (expected = PaymentTypeMismatchException.class)
    public void onlyCommissionedEmployeesCanHaveSalesCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        addSalesCard = new AddSalesCard(payrollDB, 1, monday, 100.0);
        addSalesCard.execute();
    }
}
