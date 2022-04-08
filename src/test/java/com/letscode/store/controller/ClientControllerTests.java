package com.letscode.store.controller;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testPessoaPage() throws Exception {
        when(clientService.listAll(Mockito.any())).thenReturn(Page.empty());
        mockMvc.perform(
                        get("/client/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePessoa() throws Exception {
        when(clientService.saveClient(Mockito.any())).thenReturn(new ClientDTO());

        mockMvc.perform(post("/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Eduardo\"}"))
                .andExpect(status().isCreated());
    }


}
