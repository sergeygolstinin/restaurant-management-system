package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PaymentRepository {
    private EntityManager entityManager;

    public PaymentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Payment findById(Long id) {
        try {
            return entityManager.find(Payment.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find Payment with ID: " + id, e);
        }
    }

    public List<Payment> findAll() {
        try {
            TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment p", Payment.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all payments", e);
        }
    }

    public Payment save(Payment payment) {
        try {
            if (payment.getId() == null) {
                entityManager.persist(payment);
            } else {
                payment = entityManager.merge(payment);
            }
            return payment;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save payment: " + payment, e);
        }
    }

    public void delete(Long id) {
        try {
            Payment payment = findById(id);
            if (payment != null) {
                entityManager.remove(payment);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete Payment with ID: " + id, e);
        }
    }

    // Add method to find payments by user or date range if needed
    public List<Payment> findByUserId(Long userId) {
        try {
            TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.user.id = :userId", Payment.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find payments for user ID: " + userId, e);
        }
    }
}
