package fr.ensai.demo.repository;

import fr.ensai.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Iterable<Employee> findByFirstName(String firstName);
    public Iterable<Employee> findByLastName(String lastName);
    public Iterable<Employee> findByFirstNameIgnoreCase(String firstName);
    public Iterable<Employee> findByLastNameIgnoreCase(String lastName);
}
