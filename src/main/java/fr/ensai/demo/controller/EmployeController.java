package fr.ensai.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.demo.model.Employee;
import fr.ensai.demo.service.EmployeeService;

@RestController
public class EmployeController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public Iterable<Employee> getEmployees() {
    return employeeService.getEmployees();
  }

  @GetMapping("/employees/count")
  public long countEmployees() {
    return employeeService.countEmployees();
  }

  @GetMapping("/employees/{id}")
  public Optional<Employee> getEmployee(@PathVariable Long id) {
    return employeeService.getEmployee(id);
  }

  @PostMapping("/employees")
  public Employee newEmployee(@RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

  @PostMapping("/employees/{id}/password")
  public Optional<Employee> changePassword(@PathVariable Long id) {
    return employeeService.setRandowPassword(id);
  }

}
