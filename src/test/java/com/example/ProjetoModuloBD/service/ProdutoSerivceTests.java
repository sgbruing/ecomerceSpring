package com.example.ProjetoModuloBD.service;


import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.repository.ProdutoRepository;
import com.example.ProjetoModuloBD.repository.specification.ProdutoSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProdutoSerivceTests {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    public void listByCodigoNotFoundTest(){
        Pageable page = PageRequest.of(0, 5);
        Specification<Produto> specification = Specification.where(null);

        Mockito.when(produtoRepository.findAll(specification, page)).thenReturn(Page.empty());

        Page<ProdutoResponse> produtos = produtoService.listByCodigo(null, page);

        Assertions.assertTrue(produtos.isEmpty());
    }

    @Test
    public void listByCodigoTest(){
        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(Produto.builder().codigo("A123").nome("Teste1").qtde_disponivel(12).preco(12000.0).build());
        Page<Produto> pageProdutos = new PageImpl<>(produtoList);

        Pageable page = PageRequest.of(0, 5);
        Specification<Produto> specification = Specification.where(null);
        Mockito.when(produtoRepository.findAll(specification, page)).thenReturn(pageProdutos);

        Page<ProdutoResponse> produtos = produtoService.listByCodigo(null, page);

        Assertions.assertEquals(1, produtos.getSize());
    }


}
