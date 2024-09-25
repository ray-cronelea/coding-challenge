package com.example.rqchallenge.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    int id;
    String name;
    int salary;
    int age;

    // TODO: Validate https://dummy.restapiexample.com API responses
    // API is down so no validation of the actual response can be tested.
    // @JsonProperty("salary_name") may need to be added for some responses if documentation is correct.

}
