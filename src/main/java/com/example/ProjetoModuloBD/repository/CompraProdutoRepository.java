package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.CompraProduto;
import com.example.ProjetoModuloBD.model.CompraProdutoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraProdutoRepository extends JpaRepository<CompraProduto, CompraProdutoKey> {
}
