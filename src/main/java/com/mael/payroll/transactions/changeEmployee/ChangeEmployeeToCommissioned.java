package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.BiweeklySchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;

public class ChangeEmployeeToCommissioned extends ChangeEmployee {

    private double monthlyRate;
    private double commission;

    public ChangeEmployeeToCommissioned(PayrollDBFacade payrollDB, int employeeId, double monthlyRate, double commission) {
        super(payrollDB, employeeId);
        this.monthlyRate = monthlyRate;
        this.commission  = commission;
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentType(new CommissionedPayment(monthlyRate, commission));
        employee.setPaymentSchedule(new BiweeklySchedule());
    }
}
