package com.example.ProjetoModuloBD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthServer {

    @GetMapping
    public String status(){
        return "Server OK!";
    }
}
