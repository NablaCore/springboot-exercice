package fr.ensai.demo;

import fr.ensai.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	private static EmployeeService employeeService;
	@Autowired
	@Qualifier("employeeService")
	private static EmployeeService employeeService2;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		if(DemoApplication.employeeService == DemoApplication.employeeService2){
		}
	}

}
