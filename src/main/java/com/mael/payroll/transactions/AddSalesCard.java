package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentTypes.CommissionedPayment;

public class AddSalesCard {
    private PayrollDBFacade payrollDB;
    private int employeeID;
    private int date;
    private double amount;

    public AddSalesCard(PayrollDBFacade payrollDB, int employeeId, int date, double amount) {
        this.payrollDB  = payrollDB;
        this.employeeID = employeeId;
        this.date       = date;
        this.amount     = amount;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeID);

        try {
            ((CommissionedPayment) employee.getPaymentType()).setSalesCard(new SalesCard(date, amount));
        } catch (ClassCastException e) {
            throw new PaymentTypeMismatchException();
        }
    }
}
