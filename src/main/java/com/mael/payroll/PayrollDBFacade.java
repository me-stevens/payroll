package com.mael.payroll;

import java.util.HashMap;

public class PayrollDBFacade {

    private HashMap<Integer, Employee> listOfEmployees;

    public PayrollDBFacade() {
        listOfEmployees = new HashMap<>();
    }

    public Employee getEmployee(int employeeId) {
        return listOfEmployees.get(employeeId);
    }

    public void addEmployee(int employeeId, Employee employee) {
        listOfEmployees.put(employeeId, employee);
    }
}
