package com.devops.springboot_devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello! My Spring Boot CI/CD project is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy!";
    }
}
