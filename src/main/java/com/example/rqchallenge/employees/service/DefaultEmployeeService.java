package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeResponse;
import com.example.rqchallenge.employees.model.EmployeeServiceException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.function.Supplier;


public class DefaultEmployeeService implements IEmployeeService {

    private static final String ROOT_URL = "https://dummy.restapiexample.com/api/v1";

    private final RestTemplate restTemplate;

    public DefaultEmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeServiceException {
        return executeGetRequest(ROOT_URL + "/employees");
    }

    @Override
    public Employee getEmployee(int id) throws EmployeeServiceException {
        return executeGetRequest(ROOT_URL + "/employee/" + id);
    }

    @Override
    public Employee add(Employee employee) throws EmployeeServiceException {
        String url = ROOT_URL + "/create";
        return executePostRequest(url, employee);
    }

    @Override
    public void delete(int id) throws EmployeeServiceException {
        String url = ROOT_URL + "/delete/" + id;
        try {
            restTemplate.delete(URI.create(url));
        } catch (RestClientException e) {
            throw new EmployeeServiceException(e);
        }
    }

    private <T> T executePostRequest(String url, Employee employee) throws EmployeeServiceException {
        Supplier<EmployeeResponse<T>> responseSupplier = () -> restTemplate.postForObject(url, employee, EmployeeResponse.class);
        return executeRequestWithErrorHandling(responseSupplier);
    }

    private <T> T executeGetRequest(String url) throws EmployeeServiceException {
        Supplier<EmployeeResponse<T>> responseSupplier = () -> restTemplate.getForObject(url, EmployeeResponse.class);
        return executeRequestWithErrorHandling(responseSupplier);
    }

    private <T> T executeRequestWithErrorHandling(Supplier<EmployeeResponse<T>> responseSupplier) throws EmployeeServiceException {
        try {
            EmployeeResponse<T> employeeResponse = responseSupplier.get();

            if (!employeeResponse.getStatus().equals("success")) {
                throw new EmployeeServiceException(String.format("Request was not successful. Status: %s", employeeResponse.getStatus()));
            }

            return employeeResponse.getData();

        } catch (RestClientException e) {
            throw new EmployeeServiceException(e);
        }
    }
}
