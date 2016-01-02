package com.mael.payroll;

public class MonthlyPayment implements PaymentType {
    private double monthlyRate;

    public MonthlyPayment(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }
}
