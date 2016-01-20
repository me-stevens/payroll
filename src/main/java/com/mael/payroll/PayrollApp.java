package com.mael.payroll;

import com.mael.payroll.transactions.PayDayTransaction;
import com.mael.payroll.transactions.addCard.AddSalesCard;
import com.mael.payroll.transactions.addCard.AddTimeCard;
import com.mael.payroll.transactions.addEmployee.AddCommissionedEmployee;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddHourlyEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;

import java.time.LocalDate;
import java.util.Map;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;

public class PayrollApp {

    public static void main(String... args) {

        LocalDate monday   = of(2016, JANUARY, 18);
        LocalDate friday   = of(2016, JANUARY, 22);
        PayrollDBFacade db = new PayrollDBFacade();

        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(db, 1, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        AddEmployee addHourlyEmployee = new AddHourlyEmployee(db, 2, "Ninja", "SinTown", 100.0);
        AddTimeCard addTimeCard       = new AddTimeCard(db, 2, monday, 8);
        addHourlyEmployee.execute();
        addTimeCard.execute();

        AddEmployee addCommissioned = new AddCommissionedEmployee(db, 3, "Coolio", "SeaTown", 1000.0, 10.0);
        AddSalesCard addSalesCard   = new AddSalesCard(db, 3, monday, 100);
        addCommissioned.execute();
        addSalesCard.execute();

        PayDayTransaction transaction = new PayDayTransaction(db, friday);
        transaction.execute();

        printResults(friday, transaction);
    }

    private static void printResults(LocalDate payDay, PayDayTransaction transaction) {
        System.out.println("----------------------------");
        System.out.println("          PAYROLL");
        System.out.println("----------------------------");
        System.out.println("  For pay day: " + payDay + "\n");

        System.out.println("NAME \t TIME (days) \t AMOUNT (£)");

        for(Map.Entry<Integer, Paycheck> cheque : transaction.getPayChecks().entrySet()) {
            System.out.printf("%s %11d %15.2f \n",
                    cheque.getValue().getName(),
                    cheque.getValue().getDaysWorked(),
                    cheque.getValue().getNetPay());
        }
    }

}
