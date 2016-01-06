package com.mael.payroll.paymentMethods;

public class MailMethod implements PaymentMethod {
    private String address;

    public MailMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
