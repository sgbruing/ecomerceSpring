package com.letscode.store.controller;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.model.Product;
import com.letscode.store.service.ProductService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> listAllProducts(
            @QuerydslPredicate(root = Product.class) Predicate predicate,
            Pageable pageable
    ){
        return productService.listAll(predicate, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveProduct(@RequestBody ProductDTO productDTO){
        productService.saveProduct(productDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping
    public void updateProduct(@RequestBody ProductDTO productDTO){ productService.updateProduct(productDTO);}

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{productCode}")
    public void deleteProduct(@PathVariable Integer productCode){
        productService.deleteProduct(productCode);
    }

}
