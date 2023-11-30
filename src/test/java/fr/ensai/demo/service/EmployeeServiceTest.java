package fr.ensai.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ensai.demo.model.Employee;
import fr.ensai.demo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
    @TestInstance(Lifecycle.PER_CLASS)

class EmployeeServiceTest {
    Logger LOG  = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @Autowired
    EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    


}