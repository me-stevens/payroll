package com.mael.payroll;

import java.time.LocalDate;

public class Paycheck {
    private LocalDate date;
    private Employee employee;
    private double netPay;

    public Paycheck(LocalDate date, Employee employee) {
        this.date     = date;
        this.employee = employee;
        calculateNetPay();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return employee.getName();
    }

    public int getDaysWorked() {
        return employee.getDaysWorked();
    }

    public double getNetPay() {
        return netPay;
    }

    private void calculateNetPay() {
        this.netPay = calculateGrossPay() - calculateDeductions();
    }

    private double calculateGrossPay() {
        return employee.grossPayFor(getDate());
    }

    private double calculateDeductions() {
        return employee.deductionsFor(getDate());
    }
}
