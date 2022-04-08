package com.letscode.store.controller;

import com.letscode.store.dto.RequestPurchaseDTO;
import com.letscode.store.dto.ResponsePurchaseDTO;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.Product;
import com.letscode.store.model.Purchase;
import com.letscode.store.service.PurchaseService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    @Autowired
    private final PurchaseService purchaseService;

    @GetMapping
    public Page<ResponsePurchaseDTO> listAllPurchases(@QuerydslPredicate(root = Purchase.class) Predicate predicate,
                                                      Pageable pageable){
        return purchaseService.listAll(predicate, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void savePurchase(@RequestBody RequestPurchaseDTO requestPurchaseDTO) throws NotFoundException {
        purchaseService.savePurchase(requestPurchaseDTO);
    }
}
