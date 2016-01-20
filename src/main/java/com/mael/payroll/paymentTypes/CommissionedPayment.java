package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.SalesCard;
import com.mael.payroll.paymentSchedules.PaymentSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommissionedPayment implements PaymentType {
    private double monthlyRate;
    private double commission;
    private List<SalesCard> salesCards;

    public CommissionedPayment(double monthlyRate, double commission) {
        this.monthlyRate = monthlyRate;
        this.commission  = commission;
        this.salesCards  = new ArrayList<>();
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public double getCommission() {
        return commission;
    }

    public void addSalesCard(SalesCard salesCard) {
        this.salesCards.add(salesCard);
    }

    public List<SalesCard> getAllCards() {
        return salesCards;
    }

    @Override
    public double calculatePay(LocalDate payDay, PaymentSchedule paySched) {
        double pay = 0.0;
        for (SalesCard salesCard : salesCards) {
            if (salesCard.isInPeriod(payDay, paySched)) {
                pay += calculatePayFor(salesCard);
            }
        }
        return pay;
    }

    @Override
    public double getDaysWorked(LocalDate payDay, PaymentSchedule paySched) {
        return paySched.getDaysInPeriod();
    }

    private double calculatePayFor(SalesCard salesCard) {
        return getMonthlyRate() + (salesCard.getAmount() * getCommission() / 100.0);
    }
}
