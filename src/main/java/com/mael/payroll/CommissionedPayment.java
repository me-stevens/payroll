package com.mael.payroll;

public class CommissionedPayment implements PaymentType {
    private final double commission;

    public CommissionedPayment(double commission) {
        this.commission = commission;
    }
}
