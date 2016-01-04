package com.mael.payroll.paymentTypes;

public class MonthlyPayment implements PaymentType {
    private double monthlyRate;

    public MonthlyPayment(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }
}
