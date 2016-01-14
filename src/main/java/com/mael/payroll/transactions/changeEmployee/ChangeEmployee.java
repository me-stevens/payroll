package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;

public abstract class ChangeEmployee {
    private PayrollDBFacade payrollDB;
    private int employeeId;

    public ChangeEmployee(PayrollDBFacade payrollDB, int employeeId) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);
        change(employee);
    }

    protected abstract void change(Employee employee);
}
