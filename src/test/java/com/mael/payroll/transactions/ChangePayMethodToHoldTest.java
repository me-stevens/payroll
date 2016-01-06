package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.HoldMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangePayMethodToHoldTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangePayMethodToDeposit changePayMethodToDeposit = new ChangePayMethodToDeposit(payrollDB, 1, "SquidBank", 1234);
        changePayMethodToDeposit.execute();

        ChangePayMethodToHold changePayMethodToHold = new ChangePayMethodToHold(payrollDB, 1);
        changePayMethodToHold.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void setsHoldMethod() {
        assertTrue(employee.getPaymentMethod() instanceof HoldMethod);
    }

    @Test
    public void setsTheRightAddress() {
        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();
        assertEquals("FishBowl", holdMethod.getAddress());
    }
}
