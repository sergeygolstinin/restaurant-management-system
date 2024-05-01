package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Order;

import jakarta.persistence.EntityManager;

public class OrderRepository {
    private EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }

    public void delete(Long id) {
        Order order = findById(id);
        if (order != null) {
            entityManager.remove(order);
        }
    }

    // Additional methods as needed
}
