package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.HoldMethod;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.CommissionedSchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;

public class AddCommissionedEmployee implements AddEmployeeTransaction {

    private PayrollDBFacade payrollDB;
    private int employeeId;
    private String name;
    private String address;
    private double monthlyRate;
    private double commission;

    public AddCommissionedEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double monthlyRate, double commission) {
        this.payrollDB   = payrollDB;
        this.employeeId  = employeeId;
        this.name        = name;
        this.address     = address;
        this.monthlyRate = monthlyRate;
        this.commission  = commission;
    }

    public void execute() {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new CommissionedPayment(monthlyRate, commission));
        employee.setPaymentSchedule(new CommissionedSchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }
}
