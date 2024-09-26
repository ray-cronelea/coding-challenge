package com.example.rqchallenge.employees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataUtils {

    public static int parseInteger(String id) throws ResponseStatusException {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
