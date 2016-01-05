package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddSalesCardTest {

    private Employee employee;
    private PayrollDBFacade payrollDB;
    private AddSalesCard addSalesCard;

    @Before
    public void setUp() {
        payrollDB = new PayrollDBFacade();
    }

    @Test
    public void addsSalesCardToCommissionedEmployee() {
        AddEmployee addCommissionedEmployee = new AddCommissionedEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0, 2.0);
        addCommissionedEmployee.execute();
        employee = payrollDB.getEmployee(1);

        addSalesCard = new AddSalesCard(payrollDB, 1, 10001010, 100.0);
        addSalesCard.execute();

        SalesCard salesCard = ((CommissionedPayment) employee.getPaymentType()).getSalesCard();
        assertEquals(10001010, salesCard.getDate());
        assertEquals(100.0, salesCard.getAmount(), 0.001);
    }

    @Test (expected = PaymentTypeMismatchException.class)
    public void onlyCommissionedEmployeesCanHaveSalesCards() {
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();
        employee = payrollDB.getEmployee(1);

        addSalesCard = new AddSalesCard(payrollDB, 1, 10001010, 100.0);
        addSalesCard.execute();
    }
}
