package fr.ensai.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.ensai.demo.dto.SearchInDto;
import fr.ensai.demo.model.Employee;
import fr.ensai.demo.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // get emmployees information
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
        Optional<Employee> result = employeeService.getEmployee(id);
        if (!result.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @PostMapping("/employees/search")
    public Iterable<Employee> searchEmployee(@RequestBody SearchInDto searchInDto) {
        return employeeService.searchEmployees(searchInDto.getSearch(), searchInDto.isCaseSensitive());
    }

    // add / update / delete employees
    @PostMapping("/employees")
    public ResponseEntity<Employee> newEmployee(@Valid @RequestBody Employee employee) {
        Employee newEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        boolean found = employeeService.deleteEmployee(id);
        if (!found) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Employee " + id + " supprimé", HttpStatus.OK);
    }

    // manage password
    @PatchMapping("/employees/{id}/password/random")
    public Optional<Employee> changePassword(@PathVariable Long id) {
        Optional<Employee> result = employeeService.setRandowPassword(id);
        if (!result.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

}
