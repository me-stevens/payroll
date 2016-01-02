package com.mael.payroll;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
