package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;
import com.mael.payroll.transactions.addEmployee.AddEmployee;
import com.mael.payroll.transactions.addEmployee.AddMonthlyEmployee;
import org.junit.Test;

public class DeleteEmployeeTest {

    @Test (expected = PayrollDBFacade.EmployeeNotInDBException.class)
    public void deletesEmployee() {
        PayrollDBFacade payrollDB      = new PayrollDBFacade();
        AddEmployee addMonthlyEmployee = new AddMonthlyEmployee(payrollDB, 1, "Squiddo", "FishBowl", 1000.0);
        addMonthlyEmployee.execute();
        
        DeleteEmployee deleteEmployee = new DeleteEmployee(payrollDB, 1);
        deleteEmployee.execute();
        payrollDB.getEmployee(1);
    }
}
