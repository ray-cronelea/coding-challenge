package com.example.rqchallenge.employees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataUtils {

    private DataUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int parseInteger(String input) throws ResponseStatusException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
