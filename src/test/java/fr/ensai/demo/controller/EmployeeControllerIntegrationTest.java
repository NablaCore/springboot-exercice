package fr.ensai.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import fr.ensai.demo.DemoApplication;
import fr.ensai.demo.model.Employee;
import fr.ensai.demo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repository;

    public void createTestEmployee(String firstName){
        Employee employee = new Employee(Long.valueOf(100), firstName, "Ai", "test@ensai.fr", "pwd");
        repository.save(employee);
    }

    @BeforeEach
    public void deleteAllTestEmployee(){
        repository.deleteAll();
    }

    

    @Test
    public void givenEmployees_whenGetCount_thenStatus200()
      throws Exception {
    
        createTestEmployee("bob");
        createTestEmployee("jeff");
        createTestEmployee("jack");
        createTestEmployee("pat");
    
        mvc.perform(get("/employees/count")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().string("4"));
    }


    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
      throws Exception {
    
        createTestEmployee("bob");
        createTestEmployee("bob");
        createTestEmployee("bob");
    
        mvc.perform(get("/employees")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].firstName", is("bob")));
    }
}