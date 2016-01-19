package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static java.time.DayOfWeek.FRIDAY;

public class BiweeklySchedule implements PaymentSchedule {

    private final int DAYS_IN_PERIOD = 15;

    @Override
    public boolean isPayDay(LocalDate date) {
        return isFriday(date) && isBiweekly(date);
    }

    @Override
    public int getDaysInPeriod() {
        return DAYS_IN_PERIOD;
    }

    private boolean isFriday(LocalDate date) {
        return date.getDayOfWeek() == FRIDAY;
    }

    private boolean isBiweekly(LocalDate date) {
        int weekOfYear = getWeekOfYear(date);
        return weekOfYear % 2 == 0;
    }

    private int getWeekOfYear(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }
}
