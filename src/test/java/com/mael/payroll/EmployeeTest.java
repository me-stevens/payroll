package com.mael.payroll;

import com.mael.payroll.paymentMethods.HoldMethod;
import com.mael.payroll.paymentMethods.PaymentMethod;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentSchedules.PaymentSchedule;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;
import com.mael.payroll.paymentTypes.MonthlyPayment;
import com.mael.payroll.paymentTypes.PaymentType;
import org.junit.Before;
import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
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
    public void getsPaid() {
        employee.setPaymentType(new MonthlyPayment(1000.0));
        employee.setPaymentSchedule(new MonthlySchedule());
        employee.setPaymentMethod(new HoldMethod(employee.getAddress()));

        Paycheck paycheck = new Paycheck(of(2016, JANUARY, 31));
        employee.getPaid(paycheck);
        assertEquals(1000.0, paycheck.getNetPay(), 0.001);
    }
}
