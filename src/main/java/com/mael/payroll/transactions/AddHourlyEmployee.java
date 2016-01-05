package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.HourlySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    private double hourlyRate;

    public AddHourlyEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double hourlyRate) {
        super(payrollDB, employeeId, name, address);
        this.hourlyRate = hourlyRate;
        setPaymentType(new HourlyPayment(hourlyRate));
        setPaymentSchedule(new HourlySchedule());
        setPaymentMethod();
    }
}
