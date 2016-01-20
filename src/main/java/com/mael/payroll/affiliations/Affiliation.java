package com.mael.payroll.affiliations;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public interface Affiliation {

    double getFees();

    double calculateDeductions(LocalDate payDay, PaymentSchedule paySched);

}
