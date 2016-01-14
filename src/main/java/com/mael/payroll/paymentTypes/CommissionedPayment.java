package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.SalesCard;

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
    public double calculatePay(LocalDate payDay) {
        double pay = 0.0;
        for(SalesCard salesCard : salesCards) {
            if (isInPeriod(payDay, salesCard)) {
                pay += calculatePayFor(salesCard);
            }
        }
        return pay;
    }

    private double calculatePayFor(SalesCard salesCard) {
        return getMonthlyRate() + (salesCard.getAmount() * getCommission() / 100.0);
    }

    private boolean isInPeriod(LocalDate payDay, SalesCard salesCard) {
        return salesCard.getDate().isAfter(payDay.minusDays(15));
    }
}
