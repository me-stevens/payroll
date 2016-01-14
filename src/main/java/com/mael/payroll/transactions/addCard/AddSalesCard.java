package com.mael.payroll.transactions.addCard;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import com.mael.payroll.transactions.PaymentTypeMismatchException;

import java.time.LocalDate;

public class AddSalesCard {
    private PayrollDBFacade payrollDB;
    private int employeeId;
    private LocalDate date;
    private double amount;

    public AddSalesCard(PayrollDBFacade payrollDB, int employeeId, LocalDate date, double amount) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.date       = date;
        this.amount     = amount;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);

        try {
            ((CommissionedPayment) employee.getPaymentType()).addSalesCard(new SalesCard(date, amount));
        } catch (ClassCastException e) {
            throw new PaymentTypeMismatchException();
        }
    }
}
