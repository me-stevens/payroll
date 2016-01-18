package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentTypes.MonthlyPayment;

public class ChangeEmployeeToMonthly extends ChangeEmployee {

    private double monthlyRate;

    public ChangeEmployeeToMonthly(PayrollDBFacade payrollDB, int employeeId, double monthlyRate) {
        super(payrollDB, employeeId);
        this.monthlyRate = monthlyRate;
    }

    @Override
    public void change(Employee employee) {
        employee.updatePaymentType(new MonthlyPayment(monthlyRate));
        employee.updatePaymentSchedule(new MonthlySchedule());
    }
}
