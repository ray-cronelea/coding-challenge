package com.example.rqchallenge.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse<T> {

    String status;
    T data;

}
