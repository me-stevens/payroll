package com.mael.payroll;

import com.mael.payroll.affiliations.*;
import com.mael.payroll.paymentMethods.*;
import com.mael.payroll.paymentSchedules.*;
import com.mael.payroll.paymentTypes.*;
import org.junit.Before;
import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.*;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee("name", "address");
    }

    @Test
    public void createsAnEmployee() {
        assertEquals("name", employee.getName());
        assertEquals("address", employee.getAddress());
    }

    @Test
    public void setsName() {
        employee.setName("new name");
        assertEquals("new name", employee.getName());
    }

    @Test
    public void setAddress() {
        employee.setAddress("new address");
        assertEquals("new address", employee.getAddress());
    }

    @Test
    public void setsPaymentType() {
        PaymentType payment = new HourlyPayment(8.0);
        employee.setPaymentType(payment);
        assertEquals(payment, employee.getPaymentType());
    }

    @Test
    public void setsPaymentSchedule() {
        PaymentSchedule schedule = new WeeklySchedule();
        employee.setPaymentSchedule(schedule);
        assertEquals(schedule, employee.getPaymentSchedule());
    }

    @Test
    public void setsPaymentMethod() {
        PaymentMethod method = new HoldMethod("address");
        employee.setPaymentMethod(method);
        assertEquals(method, employee.getPaymentMethod());
    }

    @Test
    public void ifNoAffiliationsHoldsANoAffiliation() {
        assertTrue(employee.getAffiliation() instanceof NoAffiliation);
    }

    @Test
    public void addsAnAffiliation() {
        UnionAffiliation unionAffiliation = new UnionAffiliation(100.0);
        employee.setAffiliation(unionAffiliation);
        assertEquals(unionAffiliation, employee.getAffiliation());
    }

    @Test
    public void aMonthlyEmployeeWithNoAffiliationGetsPaid() {
        setMonthlyEmployee(1000.0);
        Paycheck paycheck = new Paycheck(of(2016, JANUARY, 31));
        employee.getPaid(paycheck);
        assertEquals(1000.0, paycheck.getNetPay(), 0.001);
    }

    @Test
    public void aMonthlyEmployeeWithAffiliationGetsPaid() {
        setMonthlyEmployee(1000.0);
        Affiliation affiliation = new UnionAffiliation(100.0);
        employee.setAffiliation(affiliation);
        Paycheck paycheck = new Paycheck(of(2016, JANUARY, 31));
        employee.getPaid(paycheck);
        assertEquals(1000.0 - 100.0, paycheck.getNetPay(), 0.001);
    }

    @Test
    public void checksPayDayForMonthlyPayDay() {
        employee.setPaymentSchedule(new MonthlySchedule());
        assertTrue(employee.isPayDay(of(2016, JANUARY, 31)));
        assertFalse(employee.isPayDay(of(2016, JANUARY, 30)));
    }

    private void setMonthlyEmployee(double salary) {
        employee.setPaymentType(new MonthlyPayment(salary));
        employee.setPaymentSchedule(new MonthlySchedule());
        employee.setPaymentMethod(new HoldMethod(employee.getAddress()));
    }
}
