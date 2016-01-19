package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;

public class AddHourlyEmployee extends AddEmployee {

    public AddHourlyEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double hourlyRate) {
        super(payrollDB, employeeId, name, address);
        setPaymentType(new HourlyPayment(hourlyRate));
        setPaymentSchedule(new WeeklySchedule());
    }
}
