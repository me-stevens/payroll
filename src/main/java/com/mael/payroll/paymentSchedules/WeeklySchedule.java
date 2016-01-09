package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;

import static java.time.DayOfWeek.FRIDAY;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(LocalDate date) {
        return isFriday(date);
    }

    private boolean isFriday(LocalDate date) {
        return date.getDayOfWeek() == FRIDAY;
    }
}
