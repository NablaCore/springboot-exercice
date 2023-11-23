package fr.ensai.demo.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.Employee;
import fr.ensai.demo.repository.EmployeRepository;

@Service
public class EmployeeService {

    public static final String allowedCharString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*/$?;";
    
    @Autowired
    private EmployeRepository employeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeRepository.save(employee);
        return savedEmployee;
    }

    public long countEmployees() {
      return employeRepository.count();
    }

    public Optional<Employee> setRandowPassword(final Long id) {
      Optional<Employee> optEmployee = employeRepository.findById(id);
      if (optEmployee.isPresent()) {
        Employee employee = optEmployee.get();
        employee.setPassword(generatePassword(7));
        employeRepository.save(employee);
        optEmployee = Optional.ofNullable(employee);
      }
      return optEmployee;
    }


    public String generatePassword(int length) {
      StringBuilder returnValue = new StringBuilder(length);
      

      for (int i = 0; i < length; i++) {
          returnValue.append(allowedCharString.charAt(new Random().nextInt(allowedCharString.length())));
      }

      return new String(returnValue);
    }
}