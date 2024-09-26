package com.example.rqchallenge.employees.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataUtilsTest {

    @Test
    void parseIntegerHappyPath() {
        assertEquals(123, DataUtils.parseInteger("123"));
    }

    @Test
    void parseIntegerFails() {
        assertThrows(ResponseStatusException.class, () -> DataUtils.parseInteger("abc"));
    }
}