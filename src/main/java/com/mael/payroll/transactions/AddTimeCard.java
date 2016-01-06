package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.TimeCard;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class AddTimeCard {
    private PayrollDBFacade payrollDB;
    private int employeeId;
    private int date;
    private double hours;

    public AddTimeCard(PayrollDBFacade payrollDB, int employeeId, int date, double hours) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.date       = date;
        this.hours      = hours;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);

        try {
            ((HourlyPayment) employee.getPaymentType()).setTimeCard(new TimeCard(date, hours));
        } catch (ClassCastException e) {
            throw new PaymentTypeMismatchException();
        }
    }
}
