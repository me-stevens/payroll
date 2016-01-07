package com.mael.payroll.paymentSchedules;

import java.time.LocalDate;

public interface PaymentSchedule {
    boolean isPayDay(LocalDate date);
}
