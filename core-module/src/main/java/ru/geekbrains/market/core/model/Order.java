package ru.geekbrains.market.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
@NamedEntityGraph(
        name = "orders.for-front",
        attributeNodes = {
                @NamedAttributeNode(value = "items", subgraph = "items-products")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "items-products",
                        attributeNodes = {
                                @NamedAttributeNode("product")
                        }
                )
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "first_name")
    private String first_name;

    @Column
    private String username;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price")
    private int price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne()
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;
}
