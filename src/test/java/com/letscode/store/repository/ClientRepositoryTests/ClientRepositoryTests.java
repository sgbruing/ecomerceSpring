package com.letscode.store.repository.ClientRepositoryTests;

import com.letscode.store.model.Client;
import com.letscode.store.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ClientRepositoryTests {
    @Autowired
    private ClientRepository clientRepository;

}
