package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderRepository {
    private EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Order findById(Long id) {
        try {
            return entityManager.find(Order.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find Order with ID: " + id, e);
        }
    }

    public List<Order> findAll() {
        try {
            TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all orders", e);
        }
    }

    public Order save(Order order) {
        try {
            if (order.getId() == null) {
                entityManager.persist(order);
            } else {
                order = entityManager.merge(order);
            }
            return order;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save order: " + order, e);
        }
    }

    public void delete(Long id) {
        try {
            Order order = findById(id);
            if (order != null) {
                entityManager.remove(order);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete Order with ID: " + id, e);
        }
    }

    // Method to find orders by customer or other criteria
    public List<Order> findByCustomer(String customerName) {
        try {
            TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.customer.name = :customerName", Order.class);
            query.setParameter("customerName", customerName);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find orders for customer: " + customerName, e);
        }
    }
}
