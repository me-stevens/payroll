package com.mael.payroll;

import com.mael.payroll.paymentMethods.PaymentMethod;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentTypes.PaymentType;

public class Employee {

    private String name;
    private String address;
    private PaymentType paymentType;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;

    public Employee(String name, String address) {
        this.name    = name;
        this.address = address;
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

    public void getPaid(Paycheck paycheck) {
        double netPay = getPaymentType().calculatePay();
        paycheck.setNetPay(netPay);
    }
}
