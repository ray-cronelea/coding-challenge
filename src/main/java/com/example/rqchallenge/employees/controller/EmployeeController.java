package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.EmployeeNotFoundException;
import com.example.rqchallenge.employees.service.EmployeeServiceException;
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

import static com.example.rqchallenge.employees.controller.DataUtils.parseInteger;
import static java.lang.String.format;

@Controller
public class EmployeeController implements IEmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeServiceException {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) throws EmployeeNotFoundException, EmployeeServiceException {
        return new ResponseEntity<>(employeeService.getEmployee(DataUtils.parseInteger(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws EmployeeServiceException {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(EmployeeUtils.getEmployeesByNameSearch(employees, searchString), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws EmployeeServiceException {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(EmployeeUtils.getHighestSalary(employees), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws EmployeeServiceException {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(EmployeeUtils.getTopTenHighestEarningEmployeeNames(employees), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) throws EmployeeServiceException {
        String name = (String) employeeInput.get("name");
        int salary = parseInteger((String) employeeInput.get("salary"));
        int age = parseInteger((String) employeeInput.get("age"));
        return new ResponseEntity<>(employeeService.add(name, salary, age), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) throws EmployeeServiceException {
        employeeService.delete(DataUtils.parseInteger(id));
        return new ResponseEntity<>(format("Deleted %s", id), HttpStatus.OK);
    }

}
