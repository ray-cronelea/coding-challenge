package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class DataUtils {
    public static Employee deserializeEmployee(Map<String, Object> employeeInput) {
        throw new RuntimeException("Not implemented");
    }

    public static int parseInteger(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
