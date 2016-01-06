package com.mael.payroll.paymentMethods;

public class DepositMethod implements PaymentMethod {
    private String bank;
    private int account;

    public DepositMethod(String bank, int account) {
        this.bank    = bank;
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public int getAccount() {
        return account;
    }
}
