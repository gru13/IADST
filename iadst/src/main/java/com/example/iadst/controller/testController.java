package com.example.iadst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    
    @GetMapping("/")
    public String hello() {
        return "Spring Boot is running and connected to MongoDB!";
    }
}
