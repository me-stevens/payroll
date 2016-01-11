package com.mael.payroll;

import com.mael.payroll.affiliations.Affiliation;
import com.mael.payroll.affiliations.NoAffiliation;
import com.mael.payroll.paymentMethods.PaymentMethod;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentTypes.PaymentType;

import java.time.LocalDate;

public class Employee {

    private String name;
    private String address;
    private PaymentType paymentType;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private Affiliation affiliation;

    public Employee(String name, String address) {
        this.name    = name;
        this.address = address;
        affiliation  = new NoAffiliation();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void getPaid(Paycheck paycheck) {
        double grossPay   = getPaymentType().calculatePay(paycheck.getDate());
        double deductions = getAffiliation().calculateDeductions(paycheck.getDate());

        paycheck.setNetPay(grossPay - deductions);
    }

    public boolean isPayDay(LocalDate date) {
        return getPaymentSchedule().isPayDay(date);
    }
}
