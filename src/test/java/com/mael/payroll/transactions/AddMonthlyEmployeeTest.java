package com.mael.payroll.transactions;

import com.mael.payroll.*;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentTypes.MonthlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddMonthlyEmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB          = new PayrollDBFacade();
        AddEmployeeTransaction addMonthlyE = new AddMonthlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);

        addMonthlyE.execute();
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeAsMonthly() {
        assertTrue(employee.getPaymentType() instanceof MonthlyPayment);
    }

    @Test
    public void setsThePaymentScheduleAsMonthly() {
        assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}
