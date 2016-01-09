package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;
    private List<TimeCard> timeCards;
    private final double MAX_HOURS_PER_DAY;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate   = hourlyRate;
        this.timeCards    = new ArrayList<>();
        MAX_HOURS_PER_DAY = 8.0;
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
        double pay = 0.0;
        for(TimeCard timeCard : timeCards) {
            if (isInPeriod(payDay, timeCard)) {
                pay += calculatePayFor(timeCard);
            }
        }
        return pay;
    }

    private double calculatePayFor(TimeCard timeCard) {
        double extraHours  = Math.max(timeCard.getHours() - MAX_HOURS_PER_DAY, 0.0);
        double normalHours = timeCard.getHours() - extraHours;
        return (normalHours + extraHours * 1.5) * getHourlyRate();
    }

    private boolean isInPeriod(LocalDate payDay, TimeCard timeCard) {
        return timeCard.getDate().isAfter(payDay.minusDays(5));
    }

    public class TimeCardNotFoundException extends RuntimeException {
    }
}


