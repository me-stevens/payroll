package com.mael.payroll.paymentTypes;

public class CommissionedPayment implements PaymentType {
    private double monthlyRate;
    private double commission;

    public CommissionedPayment(double monthlyRate, double commission) {
        this.monthlyRate = monthlyRate;
        this.commission  = commission;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public double getCommission() {
        return commission;
    }
}
