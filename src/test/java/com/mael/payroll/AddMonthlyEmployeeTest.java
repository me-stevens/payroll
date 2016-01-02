package com.mael.payroll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddMonthlyEmployeeTest {

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        PayrollDBFacade payrollDB      = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyE = new AddMonthlyEmployee(payrollDB);
        addMonthlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeAsMonthly() {
        PayrollDBFacade payrollDB      = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyE = new AddMonthlyEmployee(payrollDB);
        addMonthlyE.execute(1, "Squiddo", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertTrue(employee.getPaymentType() instanceof MonthlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsMonthly() {
        PayrollDBFacade payrollDB      = new PayrollDBFacade();
        AddMonthlyEmployee addMonthlyE = new AddMonthlyEmployee(payrollDB);
        addMonthlyE.execute(1, "Squido", "FishBowl", 1000.0);

        Employee employee = payrollDB.getEmployee(1);
        assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }
}
