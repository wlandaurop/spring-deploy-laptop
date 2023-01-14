package com.ejercicio.ejercObResJpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String holaMundo(){
        return "Hello world, how are we!!!";
    }
}




