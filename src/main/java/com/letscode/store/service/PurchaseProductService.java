package com.letscode.store.service;

import com.letscode.store.dto.PurchaseSaveProductDTO;
import com.letscode.store.model.Product;
import com.letscode.store.model.Purchase;
import com.letscode.store.model.PurchaseProduct;
import com.letscode.store.model.PurchaseProductKey;
import com.letscode.store.repository.PurchaseProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseProductService {

    private final PurchaseProductRepository purchaseProductRepository;


    public void savePurchaseProduct(Purchase purchase, List<Product> productsToSave, List<PurchaseSaveProductDTO> productsPurchased) {
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();
        productsToSave.forEach(product -> {
            PurchaseProductKey key = new PurchaseProductKey();
            key.setIdProduct(product.getId());
            key.setIdPurchase(purchase.getId());

            productsPurchased.forEach(productPurchased -> {
                if(product.getProductCode() == productPurchased.getProductCode()){
                    PurchaseProduct purchaseProduct = new PurchaseProduct();
                    purchaseProduct.setProduct(product);
                    purchaseProduct.setPurchase(purchase);
                    purchaseProduct.setQuantityPurchased(productPurchased.getQuantity());
                    purchaseProduct.setPurchaseProductKey(key);
                    purchaseProducts.add(purchaseProduct);
                }
            });
        });
        purchaseProductRepository.saveAll(purchaseProducts);
    }
}
