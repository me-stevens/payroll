package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;

public class ChangePayMethodToHold extends ChangeEmployee {

    public ChangePayMethodToHold(PayrollDBFacade payrollDB, int employeeId) {
        super(payrollDB, employeeId);
    }

    public void change(Employee employee) {
        employee.setPaymentMethod(new HoldMethod(employee.getAddress()));
    }
}
