package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;

public class ChangePayMethodToHold extends ChangeEmployee {

    public ChangePayMethodToHold(PayrollDBFacade payrollDB, int employeeId) {
        super(payrollDB, employeeId);
    }

    @Override
    public void change(Employee employee) {
        employee.updatePaymentMethod(new HoldMethod(employee.getAddress()));
    }
}
