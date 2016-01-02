package com.mael.payroll;

public class Employee {

    private String name;
    private PaymentType paymentType;
    private MonthlySchedule paymentSchedule;
    private HoldMethod paymentMethod;

    public Employee(String name, String address) {
        this.name = name;
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

    public void setPaymentSchedule(MonthlySchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public MonthlySchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentMethod(HoldMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public HoldMethod getPaymentMethod() {
        return paymentMethod;
    }
}
