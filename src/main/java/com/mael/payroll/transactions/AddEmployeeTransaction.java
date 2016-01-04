package com.mael.payroll.transactions;

import com.mael.payroll.PayrollDBFacade;

public interface AddEmployeeTransaction {
    void execute();
    void addDB(PayrollDBFacade payrollDB);
}
