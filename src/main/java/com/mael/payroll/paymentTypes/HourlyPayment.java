package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;
    private List<TimeCard> timeCards;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.timeCards  = new ArrayList<>();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    public TimeCard getTimeCard(LocalDate date) {
        for(TimeCard timeCard : timeCards) {
            if (timeCard.getDate() == date) {
                return timeCard;
            }
        }
        throw new TimeCardNotFoundException();
    }

    @Override
    public double calculatePay(LocalDate payDay) {
        int pay = 0;
        if (timeCards.size() > 0) {
            for(TimeCard timeCard : timeCards) {
                pay += timeCard.getHours() * getHourlyRate();
            }
        }
        return pay;
    }

    public class TimeCardNotFoundException extends RuntimeException {
    }
}
