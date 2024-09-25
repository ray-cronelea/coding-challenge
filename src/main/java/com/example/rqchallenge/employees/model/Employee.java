package com.example.rqchallenge.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    int id;
    @JsonProperty("employee_name")
    String name;
    @JsonProperty("employee_salary")
    int salary;
    @JsonProperty("employee_age")
    int age;

}
