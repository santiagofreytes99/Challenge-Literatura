package com.challenge.literatura.demo;

import com.challenge.literatura.demo.Principal;
import com.challenge.literatura.demo.MenuServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaAplicacion implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaAplicacion.class, args);
    }

    @Autowired
    private MenuServicio menuService;

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(menuService);
        principal.EjecutarAplicacion();
    }
}