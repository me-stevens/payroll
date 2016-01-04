package com.mael.payroll;

public class AddMonthlyEmployee implements AddEmployeeTransaction {

    private int employeeId;
    private String name;
    private String address;
    private double monthlyRate;
    private PayrollDBFacade payrollDB;

    public AddMonthlyEmployee(int employeeId, String name, String address, double monthlyRate) {
        this.employeeId  = employeeId;
        this.name        = name;
        this.address     = address;
        this.monthlyRate = monthlyRate;
    }

    public void execute() {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new MonthlyPayment(monthlyRate));
        employee.setPaymentSchedule(new MonthlySchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }

    public void addDB(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }
}
