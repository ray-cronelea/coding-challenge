package com.example.rqchallenge;

import com.example.rqchallenge.employees.EmployeeTestUtils;
import com.example.rqchallenge.employees.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static com.example.rqchallenge.employees.EmployeeTestDataBuilder.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(TestInMemEmployeeServiceConfiguration.class)
@AutoConfigureMockMvc
class RqChallengeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemEmployeeService inMemEmployeeService;

    @BeforeEach
    private void beforeEach() {
        inMemEmployeeService.reset();
    }

    @Test
    void createEmployee() throws Exception {
        Employee createdEmployee = assertEmployeeCreatedSuccessfully("Linda Dunphy", "45", "3400");

        Employee expectedEmployee = anEmployee().withId(1).withName("Linda Dunphy").withAge(45).withSalary(3400).build();

        assertEquals(expectedEmployee, createdEmployee);
    }

    @Test
    void getHighestSalaryOfEmployee() throws Exception {
        insertTestData();

        String jsonContent = mockMvc.perform(get("/highestSalary"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(13000, Integer.valueOf(jsonContent));
    }

    @Test
    void getTop10HighestEarningEmployeeNames() throws Exception {
        insertTestData();

        String jsonContent = mockMvc.perform(get("/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<String> actualNames = objectMapper.readValue(jsonContent, List.class);

        List<String> expectedNames = List.of("Scarlett Love", "Isobel Webb", "Neila Curry", "Adil Riggs", "Bilal Dyer", "Naima Murray", "Amna Miles", "Janet Blevins", "Niamh Barrett", "Monica Donovan");
        assertEquals(expectedNames, actualNames);
    }

    @Test
    void createAndGetEmployee() throws Exception {
        assertEmployeeCreatedSuccessfully("Jane Smith", "56", "2200");

        String jsonContent = mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Employee actualEmployee = objectMapper.readValue(jsonContent, Employee.class);

        Employee expectedEmployee = anEmployee().withId(1).withName("Jane Smith").withAge(56).withSalary(2200).build();

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void deleteEmployee() throws Exception {
        assertEmployeeCreatedSuccessfully("Jane Smith", "56", "2200");

        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getEmployeesByName() throws Exception {
        insertTestData();

        String jsonContent = mockMvc.perform(get("/search/Neil"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Employee> actualEmployees = objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        assertThat(actualEmployees)
                .usingElementComparator(EmployeeTestUtils.COMPARE_EMPLOYEE_IGNORING_ID)
                .containsOnly(
                        anEmployee().withName("Neila Curry").withSalary(9000).withAge(34).build(),
                        anEmployee().withName("Neil Dalton").withSalary(3000).withAge(35).build()
                );

    }

    private void insertTestData() throws Exception {
        assertEmployeeCreatedSuccessfully("Naima Murray", "19", "3000");
        assertEmployeeCreatedSuccessfully("Amna Miles", "22", "3000");
        assertEmployeeCreatedSuccessfully("Janet Blevins", "29", "3000");
        assertEmployeeCreatedSuccessfully("Niamh Barrett", "45", "3000");
        assertEmployeeCreatedSuccessfully("Isobel Webb", "33", "10000");
        assertEmployeeCreatedSuccessfully("Neila Curry", "34", "9000");
        assertEmployeeCreatedSuccessfully("Monica Donovan", "45", "3000");
        assertEmployeeCreatedSuccessfully("Joel Delacruz", "42", "3000");
        assertEmployeeCreatedSuccessfully("Adil Riggs", "65", "6000");
        assertEmployeeCreatedSuccessfully("Neil Dalton", "35", "3000");
        assertEmployeeCreatedSuccessfully("Willow Donaldson", "23", "3000");
        assertEmployeeCreatedSuccessfully("Conrad Moran", "62", "3000");
        assertEmployeeCreatedSuccessfully("Casey Greer", "23", "2000");
        assertEmployeeCreatedSuccessfully("Dhruv Weiss", "55", "1000");
        assertEmployeeCreatedSuccessfully("Fatimah Powers", "33", "3000");
        assertEmployeeCreatedSuccessfully("Elin Wiggins", "59", "3000");
        assertEmployeeCreatedSuccessfully("Jaden Lindsey", "29", "3000");
        assertEmployeeCreatedSuccessfully("Bilal Dyer", "46", "4000");
        assertEmployeeCreatedSuccessfully("Scarlett Love", "39", "13000");
    }

    private Employee assertEmployeeCreatedSuccessfully(String name, String age, String salary) throws Exception {
        String jsonContent = mockMvc.perform(post("/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                Map.of(
                                        "name", name,
                                        "age", age,
                                        "salary", salary
                                )
                        )))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse().getContentAsString();

        return objectMapper.readValue(jsonContent, Employee.class);
    }


}
