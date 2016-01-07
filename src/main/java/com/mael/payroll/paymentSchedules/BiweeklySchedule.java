package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;

public class BiweeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(LocalDate date) {
        return false;
    }
}
