package com.mael.payroll;

public class AddMonthlyEmployee {

    private PayrollDBFacade payrollDB;

    public AddMonthlyEmployee(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }

    public void execute(int employeeId, String name, String address, double monthlyRate) {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new MonthlyPayment(monthlyRate));
        employee.setPaymentSchedule(new MonthlySchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }
}
