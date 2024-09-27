package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;

import java.util.Comparator;
import java.util.List;

public class EmployeeUtils {

    private EmployeeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Employee> getEmployeesByNameSearch(List<Employee> employees, String searchString) {
        return employees.stream()
                .filter(employee -> employee.getName().contains(searchString))
                .toList();
    }

    public static int getHighestSalary(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
    }

    public static List<String> getTopTenHighestEarningEmployeeNames(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getName)
                .limit(10L)
                .toList();
    }
}
