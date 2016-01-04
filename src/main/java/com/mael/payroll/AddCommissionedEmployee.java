package com.mael.payroll;

public class AddCommissionedEmployee implements AddEmployeeTransaction {

    private int employeeId;
    private String name;
    private String address;
    private double commission;
    private PayrollDBFacade payrollDB;

    public AddCommissionedEmployee(int employeeId, String name, String address, double commission) {
        this.employeeId = employeeId;
        this.name       = name;
        this.address    = address;
        this.commission = commission;
    }

    public void execute() {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new CommissionedPayment(commission));
        employee.setPaymentSchedule(new CommissionedSchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }

    public void addDB(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }
}
