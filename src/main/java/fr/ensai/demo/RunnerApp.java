package fr.ensai.demo;

import fr.ensai.demo.model.HelloWorld;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import fr.ensai.demo.service.BusinessService;

@Component
public class RunnerApp implements CommandLineRunner {

    @Autowired
    private ObjectProvider<BusinessService> bs ;
    @Override
    public void run(String... args) throws Exception {
    }
}
