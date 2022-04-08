package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.repository.specification.CompraSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CompraRepositoryTests {

    @Autowired
    private CompraRepository compraRepository;

    @Test
    public void findAllComprasTest() {
        Specification<Compra> specification = Specification.where(null);
        Pageable page = PageRequest.of(0,5);
        Page<Compra> compras = compraRepository.findAll(specification, page);

        Assertions.assertNotNull(compras);
        Assertions.assertEquals(5000.0, compras.getContent().get(0).getValor_total_compra());
    }

    @Test
    public void findByCPFComprasTest() {
        Specification<Compra> specification = Specification.where(CompraSpecification.filterOneByCpf("12345"));
        Pageable page = PageRequest.of(0,5);
        Page<Compra> compras = compraRepository.findAll(specification, page);

        Assertions.assertNotNull(compras);
        Assertions.assertEquals(2, compras.getContent().size());
    }

    @Test
    public void findByCPFComprasNotFoundTest() {
        Specification<Compra> specification = Specification.where(CompraSpecification.filterOneByCpf("1234"));
        Pageable page = PageRequest.of(0,5);
        Page<Compra> compras = compraRepository.findAll(specification, page);

        Assertions.assertTrue(compras.isEmpty());
    }

}
