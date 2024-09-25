package com.example.rqchallenge.employees.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(int id) {
        super(String.format("Could not find employee with id %s", id));
    }
}
