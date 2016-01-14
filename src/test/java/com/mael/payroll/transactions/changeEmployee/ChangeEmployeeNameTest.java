package com.mael.payroll.transactions.changeEmployee;

import com.mael.payroll.Employee;
import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.transactions.addEmployee.AddHourlyEmployee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeEmployeeNameTest {

    @Test
    public void changesEmployeeName() {
        PayrollDBFacade payrollDB = new PayrollDBFacade();
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addHourlyEmployee.execute();

        ChangeEmployeeName changeEmployeeName = new ChangeEmployeeName(payrollDB, 1, "Coolio");
        changeEmployeeName.execute();

        Employee employee = payrollDB.getEmployee(1);
        assertEquals("Coolio", employee.getName());
    }
}
