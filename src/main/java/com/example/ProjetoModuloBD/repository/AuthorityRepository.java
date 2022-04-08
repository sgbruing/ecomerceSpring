package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.Authority;
import com.example.ProjetoModuloBD.model.AuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> {
}
