package com.mael.payroll.transactions;

import com.mael.payroll.*;
import com.mael.payroll.paymentSchedules.CommissionedSchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddCommissionedEmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB               = new PayrollDBFacade();
        AddEmployeeTransaction addCommissionedE = new AddCommissionedEmployee(1, "Squiddo", "FishBowl", 1000.0, 2.0);
        addCommissionedE.addDB(payrollDB);

        addCommissionedE.execute();
        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void addsCommissionedEmployeeToTheDB() {
        assertEquals("Squiddo", employee.getName());
    }

    @Test
    public void setsThePaymentTypeASCommissioned() {
        assertTrue(employee.getPaymentType() instanceof CommissionedPayment);
    }

    @Test
    public void setsThePaymentScheduleAsCommissioned() {
        assertTrue(employee.getPaymentSchedule() instanceof CommissionedSchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}
