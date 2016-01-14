package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.DepositMethod;
import com.mael.payroll.transactions.addEmployee.AddHourlyEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangePayMethodToDepositTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangePayMethodToDeposit changePayMethodToDeposit = new ChangePayMethodToDeposit(payrollDB, 1, "SquidBank", 1234);
        changePayMethodToDeposit.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void setsDepositMethod() {
        assertTrue(employee.getPaymentMethod() instanceof DepositMethod);
    }

    @Test
    public void setsTheCorrectBank() {
        DepositMethod paymentMethod = (DepositMethod) employee.getPaymentMethod();
        assertEquals("SquidBank", paymentMethod.getBank());
    }

    @Test
    public void setsTheCorrectAccount() {
        DepositMethod paymentMethod = (DepositMethod) employee.getPaymentMethod();
        assertEquals(1234, paymentMethod.getAccount());
    }
}
