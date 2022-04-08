package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClientServiceTests {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testlistAll() {
        Client clientA = new Client();
        clientA.setCpf("999");
        clientA.setName("Silveira");

        Client clientB = new Client();
        clientB.setCpf("221");
        clientB.setName("Debora");

        List<Client> clients = new ArrayList<>();
        clients.add(clientA);
        clients.add(clientB);
        Page<Client> pageClients = new PageImpl<>(clients);
        Pageable page = PageRequest.of(0, 5);

        Mockito.when(clientRepository.findAll(page)).thenReturn(pageClients);

        Page<ClientDTO> pessoasReturn = clientService.listAll(page);
        Assertions.assertEquals(2, pessoasReturn.getSize());
        Assertions.assertEquals("Silveira", pessoasReturn.toList().get(0).getName());

        Mockito.verify(clientRepository, Mockito.times(1))
                .findAll(page);
    }

    @Test
    public void testCreatePessoa() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Wesley");
        clientDTO.setCpf("5555");

        Mockito.when(clientRepository.save(Mockito.any()))
                .thenReturn(Client.convert(clientDTO));

        ClientDTO clientReturn = clientService.saveClient(clientDTO);
        Assertions.assertEquals("Wesley", clientReturn.getName());
        Assertions.assertEquals("5555", clientReturn.getCpf());
    }


}
