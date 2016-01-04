package com.mael.payroll;

public interface AddEmployeeTransaction {
    void execute();
    void addDB(PayrollDBFacade payrollDB);
}
