package com.mael.payroll.transactions.addEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;
import com.mael.payroll.paymentSchedules.MonthlySchedule;
import com.mael.payroll.paymentTypes.MonthlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddMonthlyEmployeeTest {

    private Employee employee;
    MonthlyPayment monthlyPayment;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);

        addMonthlyEmployee.execute();
        employee = payrollDB.getEmployee(1);
        monthlyPayment = (MonthlyPayment) employee.getPaymentType();
    }

    @Test
    public void addsMonthlyEmployeeToTheDB() {
        assertEquals("Squiddo",  employee.getName());
        assertEquals("FishBowl", employee.getAddress());
    }

    @Test
    public void addsTheRightSalary() {
        assertEquals(1000.0, monthlyPayment.getMonthlyRate(), 0.001);
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
