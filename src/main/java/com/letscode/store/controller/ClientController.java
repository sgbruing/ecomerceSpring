package com.letscode.store.controller;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.model.Client;
import com.letscode.store.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping
    public Page<ClientDTO> listAllClients(Pageable pageable){
        return clientService.listAll(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveClient(@RequestBody @Valid ClientDTO clientDTO){
        clientService.saveClient(clientDTO);
    }

}
