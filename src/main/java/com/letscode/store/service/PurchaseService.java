package com.letscode.store.service;

import com.letscode.store.dto.PurchaseSaveProductDTO;
import com.letscode.store.dto.RequestPurchaseDTO;
import com.letscode.store.dto.ResponsePurchaseDTO;
import com.letscode.store.exceptions.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.model.Product;
import com.letscode.store.model.Purchase;
import com.letscode.store.repository.PurchaseRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class PurchaseService {


    @Autowired
    private final PurchaseRepository purchaseRepository;
    @Autowired
    private  final ClientService clientService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final PurchaseProductService purchaseProductService;

    public Page<ResponsePurchaseDTO> listAll(Predicate predicate, Pageable pageable) {
        return purchaseRepository.findAll(predicate, pageable).map(ResponsePurchaseDTO::convert);
    }

    public void savePurchase(RequestPurchaseDTO requestPurchaseDTO) throws NotFoundException {
        Client client = clientService.findByCpf(requestPurchaseDTO.getCpf());
        List<Product> productsToSave =  productService.getProductsToPurchase(requestPurchaseDTO.getProducts());
        if(productsToSave.size() != requestPurchaseDTO.getProducts().size()){
            List<String> notHave = new ArrayList<>();
            requestPurchaseDTO.getProducts().forEach((product -> {
                if(!productsToSave.contains(product)) notHave.add(Integer.toString(product.getProductCode()));
            }));
            throw new NotFoundException("Não há quantidade suficiente dos produtos a seguir: " + notHave);
        };

        Purchase purchase = purchaseRepository.save(Purchase.convert(client, LocalDateTime.now(), getTotalPurchased(productsToSave, requestPurchaseDTO.getProducts())));

        productService.updateQuantityDB(productsToSave, requestPurchaseDTO.getProducts());

        purchaseProductService.savePurchaseProduct(purchase, productsToSave, requestPurchaseDTO.getProducts());

    }
    private double getTotalPurchased(List<Product> products, List<PurchaseSaveProductDTO> requestPurchasesDTO){
        AtomicReference<Double> totalPurchased = new AtomicReference<>((double) 0F);
        products.forEach(product -> {
            requestPurchasesDTO.forEach(requestPurchaseDTO -> {
                if (requestPurchaseDTO.getProductCode() == product.getProductCode()){
                    totalPurchased.updateAndGet(v -> (double) (v + product.getPrice() * requestPurchaseDTO.getQuantity()));
                }
            });
        });
        return totalPurchased.get();
    }

}
