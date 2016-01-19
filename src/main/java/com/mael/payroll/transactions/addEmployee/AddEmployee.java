package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentTypes.PaymentType;


public abstract class AddEmployee {

    private PayrollDBFacade payrollDB;
    private int employeeId;

    private String name;
    private String address;
    private PaymentType paymentType;
    private PaymentSchedule paymentSchedule;

    public AddEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.name       = name;
        this.address    = address;
    }

    public void execute() {
        Employee employee = new Employee(name, address, getPaymentType(), getPaymentSchedule());
        payrollDB.addEmployee(employeeId, employee);
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    private PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    private PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }
}
