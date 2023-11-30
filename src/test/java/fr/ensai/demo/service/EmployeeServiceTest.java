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

    private Employee employee;


    @BeforeAll
    public void initMock(){
        String firstName = "Liberté";
        String lastName = "Egalité";
        String email = "demo@ensai.fr";
        String password = "feg4er56geg";
        employee = new Employee(null, firstName, lastName, email, password);
        ArrayList<Employee> listEmployees = new ArrayList<>();
        listEmployees.add(employee);
        ArrayList<Employee> listEmployeesEmpty = new ArrayList<>();

        Mockito.when(employeeRepository.findByFirstName(firstName)).thenReturn(listEmployees);
        Mockito.when(employeeRepository.findByLastName(lastName)).thenReturn(listEmployees);
        Mockito.when(employeeRepository.findByFirstName(lastName)).thenReturn(listEmployeesEmpty);        
        Mockito.when(employeeRepository.findByLastName(firstName)).thenReturn(listEmployeesEmpty); 

        Mockito.when(employeeRepository.findByFirstNameIgnoreCase(firstName.toLowerCase())).thenReturn(listEmployees);
        Mockito.when(employeeRepository.findByLastNameIgnoreCase(lastName.toLowerCase())).thenReturn(listEmployees);
        Mockito.when(employeeRepository.findByFirstNameIgnoreCase(lastName.toLowerCase())).thenReturn(listEmployeesEmpty);        
        Mockito.when(employeeRepository.findByLastNameIgnoreCase(firstName.toLowerCase())).thenReturn(listEmployeesEmpty);        
    }

    //test search
    @Test
    void searchShoudReturnValues(){
        ArrayList<Employee> listEmployees = new ArrayList<>();
        employeeService.searchEmployees("Liberté", true).forEach(listEmployees::add);
        LOG.info(  "searchShoudReturnValues - count: " +String.valueOf(listEmployees.size()));
        
        assertEquals(listEmployees.size(), 1);
        assertEquals(listEmployees.get(0).getFirstName(), "Liberté");

        listEmployees.clear();
        employeeService.searchEmployees("Egalité", true).forEach(listEmployees::add);
        assertEquals(listEmployees.size(), 1);
        assertEquals(listEmployees.get(0).getFirstName(), "Liberté");
    }

    // tests password
    @Test
    void generatedPasswordShouldHaveProperLength() {
        String password = employeeService.generatePassword(10);
        assertEquals(10, password.length());
    }

    @Test
    void generatedPasswordShouldContainProperChars() {
        String password = employeeService.generatePassword(10);
        boolean charsOk = true;
        for (int i = 0; i < password.length(); i++) {
            if (!charsOk) {
                break;
            }
            charsOk = EmployeeService.allowedCharString.indexOf(password.charAt(i)) >= 0;
        }
        assertTrue(charsOk);
    }



}