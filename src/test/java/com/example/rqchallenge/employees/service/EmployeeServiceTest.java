package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.example.rqchallenge.employees.EmployeeTestDataBuilder.anEmployee;
import static com.example.rqchallenge.employees.EmployeeTestDataBuilder.someEmployees;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    EmployeeService employeeService;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void beforeEach(){
        employeeService = new EmployeeService(restTemplate);
    }

    @Test
    void getEmployeesReturnsSomeEmployees() throws EmployeeServiceException {

        when(restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employees", EmployeeResponse.class))
                .thenReturn(new EmployeeResponse<>("success", someEmployees()));

        assertEquals(someEmployees(), employeeService.getEmployees());

    }

    @Test
    void getEmployeeReturnsAnEmployee() throws EmployeeServiceException, EmployeeNotFoundException {
        Employee expectedEmployee = anEmployee().withName("Jack").withAge(61).withId(1).withSalary(10000).build();

        when(restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employee/1", EmployeeResponse.class))
                .thenReturn(new EmployeeResponse<>("success", expectedEmployee));

        assertEquals(expectedEmployee, employeeService.getEmployee(1));
    }

    @Test
    void addEmployeeAndReturnCreatedEmployee() throws EmployeeServiceException {
        Employee expectedEmployee = anEmployee().withName("Jack").withAge(61).withId(1).withSalary(10000).build();

        when(restTemplate.postForObject("https://dummy.restapiexample.com/api/v1/create", expectedEmployee, EmployeeResponse.class))
                .thenReturn(new EmployeeResponse<>("success", expectedEmployee));

        assertEquals(expectedEmployee, employeeService.add(expectedEmployee));
    }

    @Test
    void deleteEmployee() throws EmployeeServiceException {
        employeeService.delete(1);
        verify(restTemplate).delete(URI.create("https://dummy.restapiexample.com/api/v1/delete/1"));
    }

    @Test
    void getEmployeeFailsDueToRestClientException() {

        RestClientException expectedNestedException = new RestClientException("Can't reach server!");
        when(restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employee/1", EmployeeResponse.class))
                .thenThrow(expectedNestedException);

        assertThrows(EmployeeServiceException.class, () -> employeeService.getEmployee(1));
    }

    @Test
    void getEmployeeReturnsFailedStatus() {

        when(restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employee/1", EmployeeResponse.class))
                .thenReturn(new EmployeeResponse("failed", null));

        EmployeeServiceException exception = assertThrows(EmployeeServiceException.class, () -> employeeService.getEmployee(1));

        assertEquals("Request was not successful. Status: failed", exception.getMessage());

    }

    @Test
    void getEmployeeReturnsNothing() {

        when(restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employee/1", EmployeeResponse.class))
                .thenReturn(new EmployeeResponse("success", null));

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(1));

        assertEquals("Could not find employee with id 1", exception.getMessage());

    }

}