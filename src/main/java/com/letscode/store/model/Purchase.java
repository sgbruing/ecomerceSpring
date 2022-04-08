package com.letscode.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Entity(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "purchase_date", nullable = false )
    private LocalDateTime purchaseDate;

    @Column(name = "total_purchased", nullable = false)
    private double totalPurchased;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseProduct> products;

    public static Purchase convert(Client client, LocalDateTime localDateTime, Double totalPurchase){
        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setPurchaseDate(localDateTime);
        purchase.setTotalPurchased(totalPurchase);
        return purchase;
    }
}
