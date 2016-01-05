package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.CommissionedSchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;

public class AddCommissionedEmployee extends AddEmployee {

    private double monthlyRate;
    private double commission;

    public AddCommissionedEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double monthlyRate, double commission) {
        super(payrollDB, employeeId, name, address);
        this.monthlyRate = monthlyRate;
        this.commission  = commission;
        setPaymentType(new CommissionedPayment(monthlyRate, commission));
        setPaymentSchedule(new CommissionedSchedule());
    }
}
