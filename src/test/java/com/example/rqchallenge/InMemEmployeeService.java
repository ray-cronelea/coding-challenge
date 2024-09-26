package com.example.rqchallenge;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemEmployeeService implements IEmployeeService {

    private final Map<Integer, Employee> employeeById = new HashMap<>();

    private int lastId = 0;

    public void reset() {
        employeeById.clear();
        lastId = 0;
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employeeById.values());
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeById.get(id);
    }

    @Override
    public Employee add(String name, int salary, int age) {
        lastId++;
        Employee employee = Employee.builder().id(lastId).name(name).salary(salary).age(age).build();
        employeeById.put(lastId, employee);
        return employee;
    }

    @Override
    public void delete(int id) {
        employeeById.remove(id);
    }
}
