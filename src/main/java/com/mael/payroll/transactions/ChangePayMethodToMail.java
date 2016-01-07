package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.MailMethod;

public class ChangePayMethodToMail extends ChangeEmployee {

    public ChangePayMethodToMail(PayrollDBFacade payrollDB, int employeeId) {
        super(payrollDB, employeeId);
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentMethod(new MailMethod(employee.getAddress()));
    }
}
