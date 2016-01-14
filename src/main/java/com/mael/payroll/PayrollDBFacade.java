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
        } else {
            throw new EmployeeNotInDBException();
        }
    }

    public void deleteEmployee(int employeeId) {
        if (employeeExists(employeeId)) {
            listOfEmployees.remove(employeeId);
        } else {
            throw new EmployeeNotInDBException();
        }
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
            throw new NotAUnionMemberException();
        }
    }

    public int getUnionEmployeeId(int memberId) {
        try {
            return unionMembers.get(memberId);
        } catch (NullPointerException e) {
            throw new NotAUnionMemberException();
        }
    }

    public int getUnionMemberId(int employeeId) {
        for (Map.Entry<Integer, Integer> unionMemberIds : unionMembers.entrySet()) {
            if (unionMemberIds.getValue() == employeeId) {
                return unionMemberIds.getKey();
            }
        }
        throw new NotAUnionMemberException();
    }

    public void deleteUnionMember(int memberId) {
        if (unionMemberExists(memberId)) {
            unionMembers.remove(memberId);
        } else {
            throw new NotAUnionMemberException();
        }
    }

    private boolean employeeExists(int employeeId) {
        return listOfEmployees.containsKey(employeeId);
    }

    private boolean unionMemberExists(int memberId) {
        return unionMembers.containsKey(memberId);
    }

    public class EmployeeNotInDBException extends RuntimeException {
    }

    public class NotAUnionMemberException extends RuntimeException {
    }
}
