package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.User;

import jakarta.persistence.EntityManager;

public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    // Additional methods as needed
}
