package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddHourlyEmployeeTest {

    @Test
    public void addsHourlyEmployeeToTheDB() {
        PayrollDBFacade payrollDB    = new PayrollDBFacade();
        AddHourlyEmployee addHourlyE = new AddHourlyEmployee(payrollDB);
        addHourlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeASHourly() {
        PayrollDBFacade payrollDB    = new PayrollDBFacade();
        AddHourlyEmployee addHourlyE = new AddHourlyEmployee(payrollDB);
        addHourlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertTrue(employee.getPaymentType() instanceof HourlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsHourly() {
        PayrollDBFacade payrollDB    = new PayrollDBFacade();
        AddHourlyEmployee addHourlyE = new AddHourlyEmployee(payrollDB);
        addHourlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertTrue(employee.getPaymentSchedule() instanceof HourlySchedule);
    }
}
