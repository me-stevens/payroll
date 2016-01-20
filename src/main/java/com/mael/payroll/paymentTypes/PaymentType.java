package com.mael.payroll.paymentTypes;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public interface PaymentType {

    double calculatePay(LocalDate payDay, PaymentSchedule paySched);

    double getDaysWorked(LocalDate payDay, PaymentSchedule paySched);
}
