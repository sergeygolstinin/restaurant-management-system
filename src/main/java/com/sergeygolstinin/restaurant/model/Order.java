package com.sergeygolstinin.restaurant.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "order_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    public Order() {
    }

    public Order(User user, Double totalPrice, Date orderTime) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    // Getters and setters
}
