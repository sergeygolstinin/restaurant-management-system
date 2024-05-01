package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.UserRepository;
import com.sergeygolstinin.restaurant.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private EntityManager entityManager;

    public UserService(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            User user = userRepository.findById(id);
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to retrieve user by ID: {}", id, e);
            throw e; // Rethrow the exception or handle it based on your application's needs
        }
    }

    public User createUser(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            User savedUser = userRepository.save(user); // Assume save returns the managed entity
            tx.commit();
            return savedUser;
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to create user: {}", user, e);
            throw e;
        }
    }

    public void deleteUser(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            userRepository.delete(id);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to delete user with ID: {}", id, e);
            throw e;
        }
    }
}
