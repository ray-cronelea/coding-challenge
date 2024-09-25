package com.example.rqchallenge.employees.model;

public class EmployeeServiceException extends Exception {

    public EmployeeServiceException(String message){
        super(message);
    }

    public EmployeeServiceException(Exception exception){
        super(exception);
    }

}
