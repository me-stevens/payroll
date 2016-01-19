package com.mael.payroll;

import com.mael.payroll.affiliations.Affiliation;
import com.mael.payroll.affiliations.NoAffiliation;
import com.mael.payroll.paymentMethods.HoldMethod;
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

    public Employee(String name, String address, PaymentType paymentType, PaymentSchedule paymentSchedule) {
        this.name            = name;
        this.address         = address;
        this.paymentType     = paymentType;
        this.paymentSchedule = paymentSchedule;
        this.paymentMethod   = new HoldMethod(address);
        this.affiliation     = new NoAffiliation();
    }

    public void updateName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void updatePaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void updatePaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void updateAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public boolean isPayDay(LocalDate date) {
        return paymentSchedule.isPayDay(date);
    }

    public double grossPayFor(LocalDate date) {
        return paymentType.calculatePay(date);
    }

    public double deductionsFor(LocalDate date) {
        return affiliation.calculateDeductions(date);
    }
}
