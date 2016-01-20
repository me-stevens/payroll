package com.mael.payroll.affiliations;

import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;

public class NoAffiliation implements Affiliation {

    @Override
    public double getFees() {
        return 0.0;
    }

    @Override
    public double calculateDeductions(LocalDate payDay, PaymentSchedule paySched) {
        return 0;
    }

}
