package com.mael.payroll;

public class AddMonthlyEmployee {

    private PayrollDBFacade payrollDB;

    public AddMonthlyEmployee(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }

    public void execute(int employeeId, String name, String address, double salary) {
        payrollDB.addEmployee(employeeId, new Employee(name, address, salary));
    }
}
