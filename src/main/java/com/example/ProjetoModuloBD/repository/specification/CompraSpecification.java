package com.example.ProjetoModuloBD.repository.specification;

import com.example.ProjetoModuloBD.model.Compra;
import org.springframework.data.jpa.domain.Specification;

public class CompraSpecification {

    public static Specification<Compra> filterOneByCpf(String cpf) {
        return (root, query, builder) ->
                builder.like(root.get("cpf"), cpf);
    }

}
