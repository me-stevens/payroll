package com.mael.payroll.paymentMethods;

public class HoldMethod implements PaymentMethod {
    private String address;

    public HoldMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
