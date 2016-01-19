package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.Paycheck;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;

public class PayDayTransaction {

    private final PayrollDBFacade payrollDB;
    private final LocalDate payDay;
    private Map<Integer, Paycheck> payChecks;

    public PayDayTransaction(PayrollDBFacade payrollDB, LocalDate payDay) {
        this.payrollDB = payrollDB;
        this.payDay    = payDay;
        this.payChecks = new HashMap<>();
    }

    public void execute() {
        for (Map.Entry<Integer, Employee> employeeRecord : payrollDB.getAllEmployees().entrySet()) {

            Employee employee = employeeRecord.getValue();

            if (employee.isPayDay(payDay)) {
                payChecks.put(
                        employeeRecord.getKey(),
                        new Paycheck(payDay, employee)
                );
            }
        }
    }

    public Map<Integer, Paycheck> getPayChecks() {
        return payChecks;
    }

    public static void main(String... args) {
        LocalDate payDay               = of(2016, JANUARY, 31);
        PayrollDBFacade db             = new PayrollDBFacade();
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(db, 1, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();

        PayDayTransaction transaction = new PayDayTransaction(db, payDay);
        transaction.execute();

        printResults(payDay, transaction);
    }

    private static void printResults(LocalDate payDay, PayDayTransaction transaction) {
        System.out.println("----------------------------");
        System.out.println("          PAYROLL");
        System.out.println("----------------------------");
        System.out.println("  For pay day: " + payDay + "\n");

        System.out.println("Name\tTime\tAmount (£)");

        for(Map.Entry<Integer, Paycheck> cheque : transaction.getPayChecks().entrySet()) {
            System.out.println(cheque.getValue().getName() + "\t" + cheque.getValue().getNetPay());
        }
    }
}
