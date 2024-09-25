package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.rqchallenge.employees.EmployeeTestDataBuilder.anEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeUtilsTest {

    @Test
    void getEmployeesByNameSearchHappyPath() {
        List<Employee> employees = List.of(
                anEmployee().withName("Beth Ballard").build(),
                anEmployee().withName("Alan Smith").build(),
                anEmployee().withName("Hussain Daniels").build(),
                anEmployee().withName("Charles Marsh").build(),
                anEmployee().withName("Junior Phelps").build(),
                anEmployee().withName("Alan Maddox").build(),
                anEmployee().withName("Josef Noble").build(),
                anEmployee().withName("Silas Kidd").build(),
                anEmployee().withName("Woody Dixon").build(),
                anEmployee().withName("Krystal Hamilton").build(),
                anEmployee().withName("Cole Blackwell").build()
        );

        List<Employee> expected = List.of(
                anEmployee().withName("Alan Smith").build(),
                anEmployee().withName("Alan Maddox").build()
        );
        assertEquals(expected, EmployeeUtils.getEmployeesByNameSearch(employees, "Alan"));
    }

    @Test
    void getEmployeesByNameSearchNoMatches() {
        List<Employee> employees = List.of(
                anEmployee().withName("Beth Ballard").build(),
                anEmployee().withName("Alan Smith").build(),
                anEmployee().withName("Hussain Daniels").build(),
                anEmployee().withName("Charles Marsh").build(),
                anEmployee().withName("Junior Phelps").build(),
                anEmployee().withName("Alan Maddox").build(),
                anEmployee().withName("Josef Noble").build(),
                anEmployee().withName("Silas Kidd").build(),
                anEmployee().withName("Woody Dixon").build(),
                anEmployee().withName("Krystal Hamilton").build(),
                anEmployee().withName("Cole Blackwell").build()
        );

        assertEquals(List.of(), EmployeeUtils.getEmployeesByNameSearch(employees, "Ray"));
    }

    @Test
    void getHighestSalary() {
        List<Employee> employees = List.of(
                anEmployee().withSalary(2000).build(),
                anEmployee().withSalary(5000).build(),
                anEmployee().withSalary(7000).build(),
                anEmployee().withSalary(3000).build(),
                anEmployee().withSalary(1000).build(),
                anEmployee().withSalary(4000).build(),
                anEmployee().withSalary(1000).build(),
                anEmployee().withSalary(3000).build(),
                anEmployee().withSalary(3000).build(),
                anEmployee().withSalary(3000).build(),
                anEmployee().withSalary(3000).build()
        );

        assertEquals(7000, EmployeeUtils.getHighestSalary(employees));
    }

    @Test
    void getHighestSalaryNoEmployees() {
        assertEquals(0, EmployeeUtils.getHighestSalary(List.of()));
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        List<Employee> employees = List.of(
                anEmployee().withName("Naima Murray").withSalary(3000).build(),
                anEmployee().withName("Amna Miles").withSalary(3000).build(),
                anEmployee().withName("Janet Blevins").withSalary(3000).build(),
                anEmployee().withName("Niamh Barrett").withSalary(3000).build(),
                anEmployee().withName("Isobel Webb").withSalary(10000).build(),
                anEmployee().withName("Eliza Curry").withSalary(9000).build(),
                anEmployee().withName("Monica Donovan").withSalary(3000).build(),
                anEmployee().withName("Joel Delacruz").withSalary(3000).build(),
                anEmployee().withName("Adil Riggs").withSalary(6000).build(),
                anEmployee().withName("Neil Dalton").withSalary(3000).build(),
                anEmployee().withName("Willow Donaldson").withSalary(3000).build(),
                anEmployee().withName("Conrad Moran").withSalary(3000).build(),
                anEmployee().withName("Casey Greer").withSalary(2000).build(),
                anEmployee().withName("Dhruv Weiss").withSalary(1000).build(),
                anEmployee().withName("Fatimah Powers").withSalary(3000).build(),
                anEmployee().withName("Elin Wiggins").withSalary(3000).build(),
                anEmployee().withName("Jaden Lindsey").withSalary(3000).build(),
                anEmployee().withName("Bilal Dyer").withSalary(4000).build(),
                anEmployee().withName("Scarlett Love").withSalary(13000).build(),
                anEmployee().withName("Grover Perkins").withSalary(11000).build()
        );

        List<String> expected = List.of(
                "Scarlett Love",
                "Grover Perkins",
                "Isobel Webb",
                "Eliza Curry",
                "Adil Riggs",
                "Bilal Dyer",
                "Naima Murray",
                "Amna Miles",
                "Janet Blevins",
                "Niamh Barrett"
        );

        assertEquals(expected, EmployeeUtils.getTopTenHighestEarningEmployeeNames(employees));
    }
}