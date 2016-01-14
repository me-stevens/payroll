package com.mael.payroll;

import java.util.HashMap;
import java.util.Map;

public class PayrollDBFacade {

    private Map<Integer, Employee> listOfEmployees;
    private Map<Integer, Integer>  unionMembers;

    public PayrollDBFacade() {
        listOfEmployees = new HashMap<>();
        unionMembers    = new HashMap<>();
    }

    public void addEmployee(int employeeId, Employee employee) {
       listOfEmployees.put(employeeId, employee);
    }

    public Employee getEmployee(int employeeId) {
        if (employeeExists(employeeId)) {
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

    public void addUnionMember(int memberId, int employeeId) {
        this.unionMembers.put(memberId, employeeId);
    }

    public Employee getUnionMember(int memberId) {
        try {
            return getEmployee(unionMembers.get(memberId));
        } catch (NullPointerException e) {
            throw new UnionMemberNotInDBException();
        }
    }

    public int getUnionEmployeeId(int memberId) {
        try {
            return unionMembers.get(memberId);
        } catch (NullPointerException e) {
            throw new UnionMemberNotInDBException();
        }
    }

    public void deleteUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    private boolean employeeExists(int employeeId) {
        return listOfEmployees.containsKey(employeeId);
    }

    public class EmployeeNotInDBException extends RuntimeException {
    }

    public class UnionMemberNotInDBException extends RuntimeException {
    }
}
