package com.mael.payroll;

import com.mael.payroll.paymentSchedules.HourlySchedule;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;
import com.mael.payroll.paymentTypes.PaymentType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void setsPaymentType() {
        PaymentType payment = new HourlyPayment(8.0);
        employee.setPaymentType(payment);
        assertEquals(payment, employee.getPaymentType());
    }

    @Test
    public void setsPaymentSchedule() {
        PaymentSchedule schedule = new HourlySchedule();
        employee.setPaymentSchedule(schedule);
        assertEquals(schedule, employee.getPaymentSchedule());
    }

    @Test
    public void setsPaymentMethod() {
        HoldMethod method = new HoldMethod();
        employee.setPaymentMethod(method);
        assertEquals(method, employee.getPaymentMethod());
    }
}
