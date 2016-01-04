package com.mael.payroll.transactions;

import com.mael.payroll.*;
import com.mael.payroll.paymentSchedules.HourlySchedule;
import com.mael.payroll.paymentTypes.HourlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddHourlyEmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB         = new PayrollDBFacade();
        AddEmployeeTransaction addHourlyE = new AddHourlyEmployee(1, "Squiddo", "FishBowl", 1000.0);
        addHourlyE.addDB(payrollDB);

        addHourlyE.execute();
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsHourlyEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeASHourly() {
        assertTrue(employee.getPaymentType() instanceof HourlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsHourly() {
        assertTrue(employee.getPaymentSchedule() instanceof HourlySchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}