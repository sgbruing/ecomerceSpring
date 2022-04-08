package com.letscode.store.service;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.dto.PurchaseSaveProductDTO;
import com.letscode.store.exceptions.InvalidValueFieldException;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.model.Product;
import com.letscode.store.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public Page<ProductDTO> listAll(Predicate predicate, Pageable pageable) {
        return productRepository.findAll(predicate, pageable).map(ProductDTO::convert);
    }

    public void saveProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findByProductCode(productDTO.getProductCode());
        if(product.isPresent()) throw new InvalidValueFieldException("Produto já existe");
        productRepository.save(Product.convert(productDTO));
    }

    public void updateProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findByProductCode(productDTO.getProductCode());
        if(product.isEmpty()) throw new InvalidValueFieldException("Produto não existe");
        product.get().setQuantity(productDTO.getQuantity());
        product.get().setPrice(productDTO.getPrice());
        productRepository.save(product.get());
    }

    public List<Product> getProductsToPurchase(List<PurchaseSaveProductDTO> purchaseSaveProductDTOS){
        List<Product> productsDB = new ArrayList<>();
        purchaseSaveProductDTOS.forEach(purchasedProduct -> {
            if(productRepository.findByProductCode(purchasedProduct.getProductCode()).isPresent()){
                Product productDB = productRepository.findByProductCode(purchasedProduct.getProductCode()).get();
                if(productDB.getQuantity() >= purchasedProduct.getQuantity()) {
//                    productDB.setQuantity(purchasedProduct.getQuantity());
                    productsDB.add(productDB);
                }
            }
        });
        return productsDB;
    }

    public void updateQuantityDB(List<Product> products, List<PurchaseSaveProductDTO> requestPurchasesDTO) {
        products.forEach(product -> {
            requestPurchasesDTO.forEach(requestPurchaseDTO -> {
                if (productRepository.findByProductCode(product.getProductCode()).isPresent()) {
                    Product productDB = productRepository.findByProductCode(product.getProductCode()).get();
                    if(productDB.getProductCode() == requestPurchaseDTO.getProductCode()){
                        productDB.setQuantity(productDB.getQuantity() - requestPurchaseDTO.getQuantity());
                        productRepository.save(productDB);
                    }
                }
            });
        });

    }

    public void deleteProduct(Integer productCode) {
        Optional<Product> product = productRepository.findByProductCode(productCode);
        if(product.isEmpty()) throw new InvalidValueFieldException("Produto não existe");
        productRepository.delete(product.get());
    }
}
