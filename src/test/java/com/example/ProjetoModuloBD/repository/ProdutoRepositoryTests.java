package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProdutoRepositoryTests {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void findByCodigoTest() {
        Optional<Produto> produto = produtoRepository.findByCodigo("A123");

        Assertions.assertTrue(produto.isPresent());
        Assertions.assertEquals("A123", produto.get().getCodigo());
    }

    @Test
    public void findByCodigoNotFoundTest() {
        Optional<Produto> produto = produtoRepository.findByCodigo("A1234");

        Assertions.assertTrue(produto.isEmpty());
    }
}
