package com.letscode.store.repository;

import com.letscode.store.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, QuerydslPredicateExecutor<Client> {

    Optional<Client> findByCpf(String cpf);
}
