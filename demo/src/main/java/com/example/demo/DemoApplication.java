package com.example.demo;

import com.example.demo.Repository.FreindRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FreindRepository repository) {
        return args -> {
            repository.findAll().forEach(System.out::println);
        };
    }
}
