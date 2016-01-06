package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentSchedules.BiweeklySchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeEmployeeToCommissionedTest {

    private Employee employee;

    @Before
    public void setup() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangeEmployeeToCommissioned changeEmployeeToCommissioned = new ChangeEmployeeToCommissioned(payrollDB, 1, 2000.0, 8.0);
        changeEmployeeToCommissioned.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void setsCommissionedPayment() {
        assertTrue(employee.getPaymentType() instanceof CommissionedPayment);
    }

    @Test
    public void setsTheRightMonthlyRate() {
        CommissionedPayment commissionedPayment = (CommissionedPayment) employee.getPaymentType();
        assertEquals(2000.0, commissionedPayment.getMonthlyRate(), 0.001);
    }

    @Test
    public void setsTheRightCommission() {
        CommissionedPayment commissionedPayment = (CommissionedPayment) employee.getPaymentType();
        assertEquals(8.0, commissionedPayment.getCommission(), 0.001);
    }

    @Test
    public void changesEmployeeToCommissionedSetsBiweeklySchedule() {
        assertTrue(employee.getPaymentSchedule() instanceof BiweeklySchedule);
    }
}
