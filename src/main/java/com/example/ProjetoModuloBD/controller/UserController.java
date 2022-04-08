package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.UserRequest;
import com.example.ProjetoModuloBD.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequest userRequest){
        userService.createUser(userRequest);
    }
}
