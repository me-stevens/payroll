package com.mael.payroll.paymentTypes;

import com.mael.payroll.cards.SalesCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class CommissionedPaymentTest {
    private CommissionedPayment commissionedPayment;
    private LocalDate fridayAndBiweekly;
    private double amount;
    private double commission;
    private double monthlyRate;

    @Before
    public void setUp() {
        monthlyRate         = 1000.0;
        commission          = 2.0;
        commissionedPayment = new CommissionedPayment(monthlyRate, commission);
        fridayAndBiweekly   = of(2016, JANUARY, 29);
        amount              = 200.0;
    }

    @Test
    public void thePayIsZeroIfNoSalesCard() {
        assertPay(0.0);
    }

    @Test
    public void thePayIsRatePlusAmountByCommissionIfOneSalesCard() {
        commissionedPayment.addSalesCard(new SalesCard(fridayAndBiweekly, amount));
        assertPay(monthlyRate + (amount * commission / 100.0));
    }

    @Test
    public void thePayIsTheSumOfRatesPlusSumOfCommissionsIfTwoSalesCards() {
        LocalDate monday = of(2016, JANUARY, 25);
        commissionedPayment.addSalesCard(new SalesCard(monday, amount));

        LocalDate tuesday = of(2016, JANUARY, 26);
        commissionedPayment.addSalesCard(new SalesCard(tuesday, amount));

        assertPay(monthlyRate + (amount * commission / 100.0) +
                monthlyRate + (amount * commission / 100.0));
    }

    @Test
    public void thePayIsCalculatedWithTheSalesCardsOfBiweeklyPeriodOnly() {
        LocalDate mondayCurrentWeek = of(2016, JANUARY, 25);
        commissionedPayment.addSalesCard(new SalesCard(mondayCurrentWeek, amount));

        LocalDate fridayThreeWeeksAgo = of(2016, JANUARY, 8);
        commissionedPayment.addSalesCard(new SalesCard(fridayThreeWeeksAgo, amount));

        assertPay(monthlyRate + (amount * commission / 100.0));
    }

    private void assertPay(double expectedPay) {
        assertEquals(expectedPay, commissionedPayment.calculatePay(fridayAndBiweekly), 0.001);
    }
}