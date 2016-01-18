package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;

public class ChangeEmployeeName extends ChangeEmployee {

    private String name;

    public ChangeEmployeeName(PayrollDBFacade payrollDB, int employeeId, String name) {
        super(payrollDB, employeeId);
        this.name = name;
    }

    @Override
    public void change(Employee employee) {
        employee.updateName(name);
    }
}
