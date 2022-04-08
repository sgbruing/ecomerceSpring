package com.letscode.store.dto;

import com.letscode.store.model.Client;
import com.letscode.store.model.Product;
import com.letscode.store.model.PurchaseProduct;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter @Setter
public class ProductDTO {
    @NotBlank @Length(min = 4, max = 4)
    private int productCode;
    @NotBlank @Positive
    private int quantity;
    @NotBlank @Positive
    private double price;

    public static ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductCode(product.getProductCode());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static ProductDTO convert(PurchaseProduct purchaseProduct) {
        ProductDTO dto = new ProductDTO();
        dto.setProductCode(purchaseProduct.getProduct().getProductCode());
        dto.setQuantity(purchaseProduct.getQuantityPurchased());
        dto.setPrice(purchaseProduct.getProduct().getPrice());
        return dto;
    }
}
