package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentTypes.MonthlyPayment;

public class AddMonthlyEmployee extends AddEmployeeTransaction {

    private double monthlyRate;

    public AddMonthlyEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double monthlyRate) {
        super(payrollDB, employeeId, name, address);
        this.monthlyRate = monthlyRate;
        setPaymentType(new MonthlyPayment(monthlyRate));
        setPaymentSchedule(new MonthlySchedule());
        setPaymentMethod();
    }
}
