package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;
    private TimeCard timeCard;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setTimeCard(TimeCard timeCard) {
        this.timeCard = timeCard;
    }

    public TimeCard getTimeCard() {
        return timeCard;
    }

    @Override
    public double calculatePay() {
        return 0;
    }
}
