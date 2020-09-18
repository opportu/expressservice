package com.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @PostMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
