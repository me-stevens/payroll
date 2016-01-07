package com.mael.payroll;

import java.time.LocalDate;

public class Paycheck {
    private LocalDate date;
    private double netPay;

    public Paycheck(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getNetPay() {
        return netPay;
    }
}
