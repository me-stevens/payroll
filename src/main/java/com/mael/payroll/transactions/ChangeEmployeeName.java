package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;

public class ChangeEmployeeName {
    private PayrollDBFacade payrollDB;
    private int employeeId;
    private String name;

    public ChangeEmployeeName(PayrollDBFacade payrollDB, int employeeId, String name) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.name       = name;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);
        employee.setName(name);
    }
}
