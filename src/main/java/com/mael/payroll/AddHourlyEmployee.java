package com.mael.payroll;

public class AddHourlyEmployee {

    private PayrollDBFacade payrollDB;

    public AddHourlyEmployee(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }

    public void execute(int employeeId, String name, String address, double hourlyRate) {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new HourlyPayment(hourlyRate));
        employee.setPaymentSchedule(new HourlySchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }
}
