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
        employee = new Employee("name", "address", new MonthlyPayment(1000.0), new MonthlySchedule());
    }

    @Test
    public void createsAnEmployee() {
        assertEquals("name", employee.getName());
        assertEquals("address", employee.getAddress());
    }

    @Test
    public void setsName() {
        employee.updateName("new name");
        assertEquals("new name", employee.getName());
    }

    @Test
    public void setAddress() {
        employee.updateAddress("new address");
        assertEquals("new address", employee.getAddress());
    }

    @Test
    public void setsPaymentType() {
        PaymentType payment = new HourlyPayment(8.0);
        employee.updatePaymentType(payment);
        assertEquals(payment, employee.getPaymentType());
    }

    @Test
    public void setsPaymentSchedule() {
        PaymentSchedule schedule = new WeeklySchedule();
        employee.updatePaymentSchedule(schedule);
        assertEquals(schedule, employee.getPaymentSchedule());
    }

    @Test
    public void setsPaymentMethod() {
        PaymentMethod method = new MailMethod("address");
        employee.updatePaymentMethod(method);
        assertEquals(method, employee.getPaymentMethod());
    }

    @Test
    public void ifNoAffiliationsHoldsANoAffiliation() {
        assertTrue(employee.getAffiliation() instanceof NoAffiliation);
    }

    @Test
    public void setsAnAffiliation() {
        UnionAffiliation unionAffiliation = new UnionAffiliation(100.0);
        employee.updateAffiliation(unionAffiliation);
        assertEquals(unionAffiliation, employee.getAffiliation());
    }

    @Test
    public void checksPayDayForMonthlyPayDay() {
        employee.updatePaymentSchedule(new MonthlySchedule());
        assertTrue(employee.isPayDay(of(2016, JANUARY, 31)));
        assertFalse(employee.isPayDay(of(2016, JANUARY, 30)));
    }
}
