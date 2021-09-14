package ru.geekbrains.trainingproject.market.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "details_id")
    private Long details_id;

    @Column(name = "status_id")
    private Long status_id;


    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrdersDetails> ordersDetailsList;

    @OneToOne()
    @JoinColumn(name = "order_id")
    private OrderStatus orderStatus;
}
