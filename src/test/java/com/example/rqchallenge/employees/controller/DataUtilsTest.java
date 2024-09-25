package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static com.example.rqchallenge.employees.EmployeeTestDataBuilder.anEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataUtilsTest {

    @Test
    void deserializeEmployeeExampleFromReadme() {
        Map<String, Object> input = Map.of("name", "test",
                "salary", "123",
                "age", "23",
                "id", 25
        );
        Employee actual = DataUtils.deserializeEmployee(input);
        assertEquals(anEmployee().withName("test").withId(25).withSalary(123).withAge(23).build(), actual);
    }

    @Test
    void deserializeEmployeeWithNonIntegerAgeInInput() {
        Map<String, Object> input = Map.of("name", "test",
                "salary", "123",
                "age", "123",
                "id", "bvid"
        );
        assertThrows(ResponseStatusException.class, () -> DataUtils.deserializeEmployee(input));
    }

    @Test
    void parseIntegerHappyPath() {
        assertEquals(123, DataUtils.parseInteger("123"));
    }

    @Test
    void parseIntegerFails() {
        assertThrows(ResponseStatusException.class, () -> DataUtils.parseInteger("abc"));
    }
}