package com.letscode.store.dto;

import com.letscode.store.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientDTO {

    @NotBlank
    private String name;
    @CPF @NotBlank
    private String cpf;

    public static ClientDTO convert(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf());
        return dto;
    }
}
