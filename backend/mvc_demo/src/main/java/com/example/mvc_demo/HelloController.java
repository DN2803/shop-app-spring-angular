package com.example.mvc_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/test/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}