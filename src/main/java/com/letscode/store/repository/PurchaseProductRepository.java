package com.letscode.store.repository;

import com.letscode.store.model.PurchaseProduct;
import com.letscode.store.model.PurchaseProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, PurchaseProductKey> {
}
