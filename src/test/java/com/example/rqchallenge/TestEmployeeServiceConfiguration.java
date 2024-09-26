package com.example.rqchallenge;

import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestEmployeeServiceConfiguration {
    @Bean
    @Primary
    public IEmployeeService mockService() {
        return new InMemEmployeeService();
    }
}
