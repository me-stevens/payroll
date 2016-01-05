package com.mael.payroll.paymentTypes;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
