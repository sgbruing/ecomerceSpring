package com.letscode.store.repository;

import com.letscode.store.model.Client;
import com.letscode.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findByProductCode(Integer product);
}
