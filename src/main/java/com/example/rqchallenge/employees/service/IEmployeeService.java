package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getEmployees() throws EmployeeServiceException;

    Employee getEmployee(int id) throws EmployeeServiceException, EmployeeNotFoundException;

    Employee add(Employee employee) throws EmployeeServiceException;

    void delete(int id) throws EmployeeServiceException;

}
