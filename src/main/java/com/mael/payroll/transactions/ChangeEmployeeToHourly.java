package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class ChangeEmployeeToHourly {
    private PayrollDBFacade payrollDB;
    private int employeeId;
    private double hourlyRate;

    public ChangeEmployeeToHourly(PayrollDBFacade payrollDB, int employeeId, double hourlyRate) {
        this.payrollDB = payrollDB;
        this.employeeId = employeeId;
        this.hourlyRate = hourlyRate;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);
        employee.setPaymentType(new HourlyPayment(hourlyRate));
        employee.setPaymentSchedule(new WeeklySchedule());
    }
}
