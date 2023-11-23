package fr.ensai.demo.service;

import fr.ensai.demo.model.Employee;

public class HelloWorld {
    private String value ="Hello World";

    public String toString(){
        return value;
    }

    public void dummi(Employee employee){
        double id = employee.getId();
    }
}
