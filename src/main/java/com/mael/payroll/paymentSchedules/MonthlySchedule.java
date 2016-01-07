package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;
import java.time.Month;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(LocalDate date) {
        return isLastDayOfMonth(date);
    }

    private boolean isLastDayOfMonth(LocalDate date) {
        Month month     = date.getMonth();
        Month nextMonth = date.plusDays(1).getMonth();
        return month != nextMonth;
    }
}
