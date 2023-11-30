package fr.ensai.demo.service;

import fr.ensai.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
//@ContextConfiguration(initializers = {EmployeeServiceTest.Initializer.class})
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    /* 
    @Container
    private static PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:13.2")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("secret");


    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresDB.getJdbcUrl(),
                    "spring.datasource.username=" + postgresDB.getUsername(),
                    "spring.datasource.password=" + postgresDB.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=create-drop"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }*/

    @Test
    void generatedPasswordShouldHaveProperLength() {
        String password = employeeService.generatePassword(10);
        assertEquals(10, password.length());
    }

    
    @Test
    void generatedPasswordShouldContainProperChars() {
        String password = employeeService.generatePassword(10);
        boolean charsOk = true;
        for(int i=0 ; i < password.length() ; i++) {
          if(!charsOk){
            break;
          }
          charsOk = EmployeeService.allowedCharString.indexOf(password.charAt(i))>=0;
        }
        assertTrue(charsOk);
    }
}