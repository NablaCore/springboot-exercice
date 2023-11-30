package fr.ensai.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.Employee;
import fr.ensai.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

  Logger LOG  = LoggerFactory.getLogger(EmployeeService.class);
  
  public static final String allowedCharString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*/$?;";

  @Autowired
  private EmployeeRepository employeRepository;

  public Optional<Employee> getEmployee(final Long id) {
    return employeRepository.findById(id);
  }

  public Iterable<Employee> getEmployees() {
    return employeRepository.findAll();
  }

  public boolean deleteEmployee(final Long id) {
    Optional<Employee> employee = getEmployee(id);
    employeRepository.deleteById(id);
    return employee.isPresent();
  }

  public Iterable<Employee> searchEmployees(String term, boolean caseSensitive) {
    LOG.info(  "searchEmployees - search: " +term + " - caseSensitive:" + (caseSensitive?"true":"false"));
    ArrayList<Employee> result = new ArrayList<>();
    Iterable<Employee> searchByFirstName;
    Iterable<Employee> searchByLastName;

    if (caseSensitive) {
      searchByFirstName = employeRepository.findByFirstName(term);
      searchByLastName = employeRepository.findByLastName(term);
    } else {
      searchByFirstName = employeRepository.findByFirstNameIgnoreCase(term);
      searchByLastName = employeRepository.findByLastNameIgnoreCase(term);

    }
    Iterator<Employee> iteratorFirstName = searchByFirstName.iterator();
    Iterator<Employee> iteratorLastName = searchByLastName.iterator();

    while (iteratorFirstName.hasNext()) {
      Employee employee = iteratorFirstName.next();
      result.add(employee);
    }
    while (iteratorLastName.hasNext()) {
      Employee employeeToAdd = iteratorLastName.next();
      boolean found = false;
      while (iteratorFirstName.hasNext()) {
        Employee employee = iteratorFirstName.next();
        if (employee.getId() == employeeToAdd.getId()) {
          found = true;
          break;
        }
      }
      if (!found) {
        result.add(employeeToAdd);
      }
    }
    LOG.info(  "searchEmployees - result count: " +String.valueOf(result.size()));
    return result;
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