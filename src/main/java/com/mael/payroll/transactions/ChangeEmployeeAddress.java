package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;

public class ChangeEmployeeAddress extends ChangeEmployee {

    private String address;

    public ChangeEmployeeAddress(PayrollDBFacade payrollDB, int employeeId, String address) {
        super(payrollDB, employeeId);
        this.address = address;
    }

    @Override
    public void change(Employee employee) {
        employee.setAddress(address);
    }
}
