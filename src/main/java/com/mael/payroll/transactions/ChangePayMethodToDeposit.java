package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.DepositMethod;

public class ChangePayMethodToDeposit extends ChangeEmployee {

    private String bank;
    private int account;

    public ChangePayMethodToDeposit(PayrollDBFacade payrollDB, int employeeId, String bank, int account) {
        super(payrollDB, employeeId);
        this.bank    = bank;
        this.account = account;
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentMethod(new DepositMethod(bank, account));
    }
}
