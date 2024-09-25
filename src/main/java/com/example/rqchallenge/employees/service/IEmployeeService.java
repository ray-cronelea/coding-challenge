package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeServiceException;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getEmployees() throws EmployeeServiceException;

    Employee getEmployee(int id) throws EmployeeServiceException;

    Employee add(Employee employee) throws EmployeeServiceException;

    void delete(int id) throws EmployeeServiceException;

}
