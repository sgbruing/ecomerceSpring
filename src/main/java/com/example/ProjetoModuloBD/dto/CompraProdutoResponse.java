package com.example.ProjetoModuloBD.dto;

import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.model.CompraProduto;
import com.example.ProjetoModuloBD.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CompraProdutoResponse {

    private String codigo;
    private String nome;
    private Double preco_unitario;
    private Integer quantidade;


    public static CompraProdutoResponse convert(CompraProduto produto){
        CompraProdutoResponse produtoResponse = new CompraProdutoResponse();

        produtoResponse.setCodigo(produto.getProduto().getCodigo());
        produtoResponse.setNome(produto.getProduto().getNome());
        produtoResponse.setPreco_unitario(produto.getProduto().getPreco());
        produtoResponse.setQuantidade(produto.getQuantidade());
        return produtoResponse;
    }
}
