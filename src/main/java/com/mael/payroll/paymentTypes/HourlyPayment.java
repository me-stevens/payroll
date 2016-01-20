package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.TimeCard;
import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HourlyPayment implements PaymentType {
    private double hourlyRate;
    private List<TimeCard> timeCards;
    private final double MAX_HOURS_PER_DAY;
    private final double BONUS;

    public HourlyPayment(double hourlyRate) {
        this.hourlyRate   = hourlyRate;
        this.timeCards    = new ArrayList<>();
        MAX_HOURS_PER_DAY = 8.0;
        BONUS             = 0.5;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    public List<TimeCard> getAllCards() {
        return timeCards;
    }

    @Override
    public double calculatePay(LocalDate payDay, PaymentSchedule paySched) {
        double pay = 0.0;
        for (TimeCard timeCard : timeCards) {
            if (timeCard.isInPeriod(payDay, paySched)) {
                pay += calculatePayFor(timeCard);
            }
        }
        return pay;
    }

    @Override
    public double getDaysWorked(LocalDate payDay, PaymentSchedule paySched) {
        int hours = 0;
        for (TimeCard timeCard : timeCards) {
            if (timeCard.isInPeriod(payDay, paySched)) {
                hours += timeCard.getHours();
            }
        }
        return hours / MAX_HOURS_PER_DAY;
    }

    private double calculatePayFor(TimeCard timeCard) {
        double extraHours = Math.max(timeCard.getHours() - MAX_HOURS_PER_DAY, 0.0);
        return timeCard.getHours() * hourlyRate + extraHours * hourlyRate * BONUS;
    }
}


