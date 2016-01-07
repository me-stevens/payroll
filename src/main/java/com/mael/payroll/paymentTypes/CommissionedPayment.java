package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.SalesCard;

public class CommissionedPayment implements PaymentType {
    private double monthlyRate;
    private double commission;
    private SalesCard salesCard;

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

    public void setSalesCard(SalesCard salesCard) {
        this.salesCard = salesCard;
    }

    public SalesCard getSalesCard() {
        return salesCard;
    }

    @Override
    public double calculatePay() {
        return 0;
    }
}
