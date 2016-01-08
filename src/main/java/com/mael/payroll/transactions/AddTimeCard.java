package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.cards.TimeCard;
import com.mael.payroll.paymentTypes.HourlyPayment;

import java.time.LocalDate;

public class AddTimeCard {
    private PayrollDBFacade payrollDB;
    private int employeeId;
    private LocalDate date;
    private double hours;

    public AddTimeCard(PayrollDBFacade payrollDB, int employeeId, LocalDate date, double hours) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.date       = date;
        this.hours      = hours;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);

        try {
            ((HourlyPayment) employee.getPaymentType()).addTimeCard(new TimeCard(date, hours));
        } catch (ClassCastException e) {
            throw new PaymentTypeMismatchException();
        }
    }
}
