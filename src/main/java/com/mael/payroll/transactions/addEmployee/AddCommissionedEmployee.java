package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.BiweeklySchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;

public class AddCommissionedEmployee extends AddEmployee {

    public AddCommissionedEmployee(PayrollDBFacade payrollDB, int employeeId, String name, String address, double monthlyRate, double commission) {
        super(payrollDB, employeeId, name, address);
        setPaymentType(new CommissionedPayment(monthlyRate, commission));
        setPaymentSchedule(new BiweeklySchedule());
    }
}
