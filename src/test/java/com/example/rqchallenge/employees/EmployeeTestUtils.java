package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.model.Employee;

import java.util.Comparator;

public class EmployeeTestUtils {
    public static final Comparator<? super Employee> COMPARE_EMPLOYEE_IGNORING_ID = (Comparator<Employee>) (e1, e2) -> {

        if (!e1.getName().equals(e2.getName())) {
            return -1;
        }

        if (e1.getSalary() != (e2.getSalary())) {
            return -1;
        }

        if (e1.getAge() != (e2.getAge())) {
            return -1;
        }

        return 0;
    };
}
