package com.letscode.store.controller;

import com.letscode.store.dto.RequestPurchaseDTO;
import com.letscode.store.dto.UserDTO;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.User;
import com.letscode.store.service.PurchaseService;
import com.letscode.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }
    
}
