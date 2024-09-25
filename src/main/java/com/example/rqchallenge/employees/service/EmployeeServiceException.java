package com.example.rqchallenge.employees.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeServiceException extends Exception {

    public EmployeeServiceException(String message){
        super(message);
    }

    public EmployeeServiceException(Exception exception){
        super(exception);
    }

}
