package com.mael.payroll.paymentTypes;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public class MonthlyPayment implements PaymentType {
    private double monthlyRate;

    public MonthlyPayment(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    @Override
    public double calculatePay(LocalDate payDay, PaymentSchedule paySched) {
        return getMonthlyRate();
    }

    @Override
    public double getDaysWorked(LocalDate payDay, PaymentSchedule paySched) {
        return paySched.getDaysInPeriod();
    }
}
