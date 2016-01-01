package com.mael.payroll;

public class Employee {

    private String name;
    private MonthlyPayment paymentType;

    public Employee(String name, String address) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPaymentType(MonthlyPayment paymentType) {
        this.paymentType = paymentType;
    }

    public MonthlyPayment getPaymentType() {
        return paymentType;
    }
}
