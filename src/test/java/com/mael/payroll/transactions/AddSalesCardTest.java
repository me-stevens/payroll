package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class AddSalesCardTest {

    private Employee employee;
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
        employee = payrollDB.getEmployee(1);

        addSalesCard = new AddSalesCard(payrollDB, 1, monday, 100.0);
        addSalesCard.execute();

        SalesCard salesCard = ((CommissionedPayment) employee.getPaymentType()).getSalesCard(monday);
        assertEquals(monday, salesCard.getDate());
        assertEquals(100.0, salesCard.getAmount(), 0.001);
    }

    @Test (expected = PaymentTypeMismatchException.class)
    public void onlyCommissionedEmployeesCanHaveSalesCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();
        employee = payrollDB.getEmployee(1);

        addSalesCard = new AddSalesCard(payrollDB, 1, monday, 100.0);
        addSalesCard.execute();
    }
}
