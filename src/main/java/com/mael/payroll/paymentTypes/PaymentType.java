package com.mael.payroll.paymentTypes;

import java.time.LocalDate;

public interface PaymentType {
    double calculatePay(LocalDate payday);
}
