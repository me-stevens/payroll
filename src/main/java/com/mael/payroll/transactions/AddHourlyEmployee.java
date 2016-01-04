package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.HoldMethod;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.HourlySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class AddHourlyEmployee implements AddEmployeeTransaction {

    private int employeeId;
    private String name;
    private String address;
    private double hourlyRate;
    private PayrollDBFacade payrollDB;

    public AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.employeeId = employeeId;
        this.name       = name;
        this.address    = address;
        this.hourlyRate = hourlyRate;
    }

    public void execute() {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new HourlyPayment(hourlyRate));
        employee.setPaymentSchedule(new HourlySchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }

    public void addDB(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }
}