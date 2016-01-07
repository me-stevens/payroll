package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(LocalDate date) {
        return false;
    }
}
