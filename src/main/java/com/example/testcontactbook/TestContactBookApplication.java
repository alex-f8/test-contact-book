package com.example.testcontactbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestContactBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestContactBookApplication.class, args);
        System.out.println();
        System.out.println("swagger: http://localhost:8080/api/swagger-ui/index.html");
        System.out.println("admin credentials: admin:123");
        System.out.println("test user 1 credentials: user:123");
        System.out.println("test user 2 credentials: test:123");
        System.out.println("finish session : http://localhost:8080/api/logout");
    }

}
