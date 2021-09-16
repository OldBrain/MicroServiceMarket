package ru.geekbrains.trainingproject.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "productTitle")
    private String productTitle;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "price_per_product")
    private Integer pricePerProduct;

//    @Column(name = "order_id")
//    private Long orderId;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    Order order;


}




