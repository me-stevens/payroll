package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;
import com.mael.payroll.paymentSchedules.BiweeklySchedule;
import com.mael.payroll.paymentTypes.CommissionedPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddCommissionedEmployeeTest {

    private Employee employee;
    private CommissionedPayment commissionedPayment;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddEmployee addCommissionedEmployee = new AddCommissionedEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0, 2.0);

        addCommissionedEmployee.execute();
        employee = payrollDB.getEmployee(1);
        commissionedPayment = (CommissionedPayment) employee.getPaymentType();
    }

    @Test
    public void addsCommissionedEmployeeToTheDB() {
        assertEquals("Squiddo",  employee.getName());
        assertEquals("FishBowl", employee.getAddress());
    }

    @Test
    public void addsTheRightSalary() {
        assertEquals(1000.0, commissionedPayment.getMonthlyRate(), 0.001);
    }

    @Test
    public void addsTheRightCommission() {
        assertEquals(2.0, commissionedPayment.getCommission(), 0.001);
    }

    @Test
    public void setsThePaymentTypeASCommissioned() {
        assertTrue(employee.getPaymentType() instanceof CommissionedPayment);
    }

    @Test
    public void setsThePaymentScheduleAsCommissioned() {
        assertTrue(employee.getPaymentSchedule() instanceof BiweeklySchedule);
    }

    @Test
    public void setsThePaymentMethodAsHold() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }
}
