package com.mael.payroll.paymentTypes;

public class CommissionedPayment implements PaymentType {
    private double commission;

    public CommissionedPayment(double commission) {
        this.commission = commission;
    }
}
