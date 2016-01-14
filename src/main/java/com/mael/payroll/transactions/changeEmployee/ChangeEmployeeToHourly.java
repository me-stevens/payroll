package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class ChangeEmployeeToHourly extends ChangeEmployee {

    private double hourlyRate;

    public ChangeEmployeeToHourly(PayrollDBFacade payrollDB, int employeeId, double hourlyRate) {
        super(payrollDB, employeeId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentType(new HourlyPayment(hourlyRate));
        employee.setPaymentSchedule(new WeeklySchedule());
    }
}
