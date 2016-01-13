package com.mael.payroll.affiliations;

import java.time.LocalDate;

public interface Affiliation {

    double getFees();

    double calculateDeductions(LocalDate payDay);

}
