package com.mael.payroll.transactions;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.paymentMethods.MailMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangePayMethodToMailTest {

    private Employee employee;

    @Before
    public void setUp() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangePayMethodToMail changePayMethodToMail = new ChangePayMethodToMail(payrollDB, 1);
        changePayMethodToMail.execute();

        employee = payrollDB.getEmployee(1);
    }

    @Test
    public void setsMailMethod() {
        assertTrue(employee.getPaymentMethod() instanceof MailMethod);
    }

    @Test
    public void setsTheCorrectAddress() {
        MailMethod mailMethod = (MailMethod) employee.getPaymentMethod();
        assertEquals("FishBowl", mailMethod.getAddress());
    }
}
