package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exceptions.InvalidValueFieldException;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public Page<ClientDTO> listAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientDTO::convert);
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Optional<Client> client = clientRepository.findByCpf(clientDTO.getCpf());
        if(client.isPresent()) throw new InvalidValueFieldException("Cliente já existe");

        return ClientDTO.convert(clientRepository.save(Client.convert(clientDTO)));
    }

    public Client findByCpf(String cpf) throws NotFoundException {
        return clientRepository.findByCpf(cpf).orElseThrow(()-> new NotFoundException("Cliente não encontrado."));
    }
}
