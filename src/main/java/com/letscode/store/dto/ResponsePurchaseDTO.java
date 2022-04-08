package com.letscode.store.dto;

import com.letscode.store.model.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ResponsePurchaseDTO {
    private LocalDateTime purchaseDate;
    private Double totalPurchased;
    private ClientDTO client;
    private List<ProductDTO> products;

    public static ResponsePurchaseDTO convert(Purchase purchase){
        ResponsePurchaseDTO dto = new ResponsePurchaseDTO();
        dto.setClient(ClientDTO.convert(purchase.getClient()));
        dto.setPurchaseDate(purchase.getPurchaseDate());
        dto.setTotalPurchased(purchase.getTotalPurchased());
        dto.setProducts(purchase.getProducts().stream().map(ProductDTO::convert).collect(Collectors.toList()));
        return dto;
    }
}
