package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class DataUtils {
    public static Employee deserializeEmployee(Map<String, Object> employeeInput) throws ResponseStatusException {
        try {
            return Employee.builder()
                    .id((Integer) employeeInput.get("id"))
                    .name((String) employeeInput.get("name"))
                    .age(parseInteger((String) employeeInput.get("age")))
                    .salary(parseInteger((String) employeeInput.get("salary")))
                    .build();
        } catch (ClassCastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public static int parseInteger(String id) throws ResponseStatusException {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
