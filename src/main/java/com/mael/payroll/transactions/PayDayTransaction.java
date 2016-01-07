package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.Paycheck;
import com.mael.payroll.PayrollDBFacade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
                Paycheck payCheck = new Paycheck(payDay);
                payChecks.put(employeeRecord.getKey(), payCheck);
                employee.getPaid(payCheck);
            }
        }
    }

    public Map<Integer, Paycheck> getPayChecks() {
        return payChecks;
    }
}
