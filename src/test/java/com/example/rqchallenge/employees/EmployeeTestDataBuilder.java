package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.model.Employee;

import java.util.List;

public class EmployeeTestDataBuilder {

    private int id = 0;
    private String name = "";
    private int salary = 0;
    private int age = 0;

    public static EmployeeTestDataBuilder anEmployee() {
        return new EmployeeTestDataBuilder();
    }

    public EmployeeTestDataBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public EmployeeTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeTestDataBuilder withSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeTestDataBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public Employee build() {
        return new Employee(id, name, salary, age);
    }

    public static List<Employee> someEmployees() {
        return List.of(
                anEmployee().withName("Jack").withAge(61).withId(1).withSalary(10000).build(),
                anEmployee().withName("Jill").withAge(45).withId(2).withSalary(12000).build(),
                anEmployee().withName("Jane").withAge(23).withId(3).withSalary(16000).build()
        );
    }

}
