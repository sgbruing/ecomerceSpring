package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
