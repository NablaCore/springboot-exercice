package fr.ensai.demo.service;

import fr.ensai.demo.model.HelloWorld;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BusinessService {
    private String name ="";

    public BusinessService(String name) {
        this.name = name;
    }

    public HelloWorld getHelloWorld(){
            System.out.println(name);
        return new HelloWorld();
    };
}
