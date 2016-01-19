package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;

import static java.time.DayOfWeek.FRIDAY;

public class WeeklySchedule implements PaymentSchedule {

    private final int DAYS_IN_PERIOD = 5;

    @Override
    public boolean isPayDay(LocalDate date) {
        return isFriday(date);
    }

    @Override
    public int getDaysInPeriod() {
        return DAYS_IN_PERIOD;
    }

    private boolean isFriday(LocalDate date) {
        return date.getDayOfWeek() == FRIDAY;
    }
}
