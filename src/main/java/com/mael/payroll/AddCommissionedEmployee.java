package com.mael.payroll;

public class AddCommissionedEmployee {

    private PayrollDBFacade payrollDB;

    public AddCommissionedEmployee(PayrollDBFacade payrollDB) {
        this.payrollDB = payrollDB;
    }

    public void execute(int employeeId, String name, String address, double commission) {
        Employee employee = new Employee(name, address);

        employee.setPaymentType(new CommissionedPayment(commission));
        employee.setPaymentSchedule(new CommissionedSchedule());
        employee.setPaymentMethod(new HoldMethod());

        payrollDB.addEmployee(employeeId, employee);
    }
}
