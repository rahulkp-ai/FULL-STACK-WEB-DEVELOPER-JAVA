package com.roni.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Hello Rahul! Your Spring Boot app is up and running on macOS! 🚀";
    }
}