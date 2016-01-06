package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;

public class ChangeEmployeeAddress {

    private PayrollDBFacade payrollDB;
    private int employeeId;
    private String address;

    public ChangeEmployeeAddress(PayrollDBFacade payrollDB, int employeeId, String address) {
        this.payrollDB  = payrollDB;
        this.employeeId = employeeId;
        this.address    = address;
    }

    public void execute() {
        Employee employee = payrollDB.getEmployee(employeeId);
        employee.setAddress(address);
    }
}
