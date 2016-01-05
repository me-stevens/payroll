package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.HoldMethod;
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
    private HoldMethod paymentMethod;

    public AddEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.name       = name;
        this.address    = address;
    }

    void execute() {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(getPaymentType());
        employee.setPaymentSchedule(getPaymentSchedule());
        employee.setPaymentMethod(getPaymentMethod());

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

    public void setPaymentMethod() {
        this.paymentMethod = new HoldMethod();
    }

    public HoldMethod getPaymentMethod() {
        return paymentMethod;
    }
}
