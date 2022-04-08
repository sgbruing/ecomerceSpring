package com.letscode.store.repository;

import com.letscode.store.model.Product;
import com.letscode.store.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, QuerydslPredicateExecutor<Purchase> {
}
