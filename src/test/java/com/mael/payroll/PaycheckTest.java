package com.mael.payroll;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PaycheckTest {

    private LocalDate payday;
    private SpyEmployee employee;
    private Paycheck paycheck;

    @Before
    public void setUp() {
        payday = of(2016, JANUARY, 31);
    }

    @Test
    public void getsEmployeeName() {
        employee = new SpyEmployee(900, 0);
        paycheck = new Paycheck(payday, employee);
        assertEquals("Fake", paycheck.getName());
    }

    @Test
    public void getsTimeWorked() {
        employee = new SpyEmployee(200, 0);
        paycheck = new Paycheck(payday, employee);
        assertEquals(15, paycheck.getDaysWorked(), 0.001);
        assertTrue(employee.getDaysWorkedWasCalled());
    }

    @Test
    public void employeeWithNoDeductionsGetsPaid() {
        employee = new SpyEmployee(500, 0);
        paycheck = new Paycheck(payday, employee);
        assertEquals(500.0, paycheck.getNetPay(), 0.001);
        assertTrue(employee.grossPayForCalledWith(payday));
    }

    @Test
    public void employeeWithDeductionsGetsPaid() {
        employee = new SpyEmployee(1000, 100);
        paycheck = new Paycheck(payday, employee);
        assertEquals(900.0, paycheck.getNetPay(), 0.001);
        assertTrue(employee.deductionsForCalledWith(payday));
    }

    class SpyEmployee extends Employee {
        private double gross;
        private double deductions;
        private LocalDate deductionsForCalledWith;
        private LocalDate grossPayForCalledWith;
        private boolean getDaysWorkedWasCalled;

        public SpyEmployee(double gross, double deductions) {
            super("Fake", "address", null, null);

            this.gross = gross;
            this.deductions = deductions;
            getDaysWorkedWasCalled = false;
        }

        @Override
        public double deductionsFor(LocalDate date) {
            deductionsForCalledWith = date;
            return deductions;
        }

        @Override
        public double grossPayFor(LocalDate date) {
            grossPayForCalledWith = date;
            return gross;
        }

        @Override
        public double getDaysWorked(LocalDate date) {
            getDaysWorkedWasCalled = true;
            return 15;
        }

        boolean deductionsForCalledWith(LocalDate date) {
            return date == deductionsForCalledWith;
        }

        boolean grossPayForCalledWith(LocalDate date) {
            return date == grossPayForCalledWith;
        }
        boolean getDaysWorkedWasCalled() {
            return getDaysWorkedWasCalled;
        }
    }
}