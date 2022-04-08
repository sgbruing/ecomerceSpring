package com.example.ProjetoModuloBD.repository.specification;

import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecification {

    public static Specification<Produto> filterOneByCodigo(String codigo) {
        return (root, query, builder) ->
                builder.like(root.get("codigo"), codigo);
    }

}
