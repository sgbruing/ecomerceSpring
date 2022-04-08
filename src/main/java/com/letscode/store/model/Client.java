package com.letscode.store.model;

import com.letscode.store.dto.ClientDTO;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;


    public static Client convert(ClientDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        return client;
    }
}
