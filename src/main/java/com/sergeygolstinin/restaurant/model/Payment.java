package com.sergeygolstinin.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;  // e.g., "cash", "credit card", "online"

    public Payment() {
    }

    public Payment(Order order, Double amount, String paymentMethod) {
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
