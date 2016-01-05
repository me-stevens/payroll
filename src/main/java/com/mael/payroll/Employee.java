package com.mael.payroll;

import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentTypes.PaymentType;

public class Employee {

    private String name;
    private String address;
    private PaymentType paymentType;
    private PaymentSchedule paymentSchedule;
    private HoldMethod paymentMethod;

    public Employee(String name, String address) {
        this.name    = name;
        this.address = address;
    }

    public String getName() {
        return name;
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

    public void setPaymentMethod(HoldMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public HoldMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getAddress() {
        return address;
    }
}
