package com.mael.payroll;

public class AddMonthlyEmployee {

    private PayrollDBFacade payrollDB;

    public AddMonthlyEmployee(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }

    public void execute(int employeeId, String name, String address, double salary) {
        Employee employee = new Employee(name, address);
        employee.setPaymentType(new MonthlyPayment(salary));
        employee.setPaymentSchedule(new MonthlySchedule());
        payrollDB.addEmployee(employeeId, employee);
    }
}
