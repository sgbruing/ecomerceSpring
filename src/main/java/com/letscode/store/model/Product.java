package com.letscode.store.model;

import com.letscode.store.dto.ProductDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_code", nullable = false)
    private Integer productCode;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private double price;

//    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "product")
    private List<PurchaseProduct> purchaseProducts;

    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setProductCode(dto.getProductCode());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        return product;
    }


}
