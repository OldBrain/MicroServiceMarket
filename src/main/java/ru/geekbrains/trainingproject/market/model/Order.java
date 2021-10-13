package ru.geekbrains.trainingproject.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany
            (mappedBy = "order",
                    cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItems> ordersItems;




    @OneToOne()
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public Integer getSum() {
        return sum;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItems> getOrdersItems() {
        return ordersItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrdersItems(List<OrderItems> ordersItems) {
        this.ordersItems = ordersItems;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", sum=" + sum +
                ", patronymic='" + patronymic + '\'' +
                ", first_name='" + first_name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
