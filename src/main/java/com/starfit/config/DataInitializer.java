package com.starfit.config;

import com.starfit.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PlanoService planoService;

    @Override
    public void run(String... args) throws Exception {
        planoService.inicializarPlanos();
    }
}







