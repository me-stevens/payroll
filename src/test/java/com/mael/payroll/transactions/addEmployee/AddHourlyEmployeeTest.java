package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;
import com.mael.payroll.paymentSchedules.WeeklySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddHourlyEmployeeTest {

    private Employee employee;
    HourlyPayment hourlyPayment;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);

        addHourlyEmployee.execute();
        employee = payrollDB.getEmployee(1);
        hourlyPayment = (HourlyPayment) employee.getPaymentType();
    }

    @Test
    public void addsHourlyEmployeeToTheDB() {
        assertEquals("Squiddo",  employee.getName());
        assertEquals("FishBowl", employee.getAddress());
    }

    @Test
    public void addsTheRightHourlyPay() {
        assertEquals(1000.0, hourlyPayment.getHourlyRate(), 0.001);
    }

    @Test
    public void setsThePaymentTypeASHourly() {
        assertTrue(employee.getPaymentType() instanceof HourlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsHourly() {
        assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}