package com.mael.payroll;

import java.util.HashMap;
import java.util.Map;

public class PayrollDBFacade {

    private Map<Integer, Employee> listOfEmployees;

    public PayrollDBFacade() {
        listOfEmployees = new HashMap<>();
    }

    public void addEmployee(int employeeId, Employee employee) {
       listOfEmployees.put(employeeId, employee);
    }

    public Employee getEmployee(int employeeId) {
        if (listOfEmployees.containsKey(employeeId)) {
            return listOfEmployees.get(employeeId);
        }
        throw new EmployeeNotInDBException();
    }

    public void deleteEmployee(int employeeId) {
        listOfEmployees.remove(employeeId);
    }

    public Map<Integer, Employee> getAllEmployees() {
        return listOfEmployees;
    }

    public class EmployeeNotInDBException extends RuntimeException {
    }
}
