package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Payment;

import jakarta.persistence.EntityManager;

public class PaymentRepository {
    private EntityManager entityManager;

    public PaymentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Payment findById(Long id) {
        return entityManager.find(Payment.class, id);
    }

    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    public void delete(Long id) {
        Payment payment = findById(id);
        if (payment != null) {
            entityManager.remove(payment);
        }
    }

    // Additional methods as needed
}
