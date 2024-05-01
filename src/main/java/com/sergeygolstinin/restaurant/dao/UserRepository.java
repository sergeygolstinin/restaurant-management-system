package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findById(Long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find User with ID: " + id, e);
        }
    }

    public List<User> findAll() {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }

    public User save(User user) {
        try {
            if (user.getId() == null) {
                entityManager.persist(user);
            } else {
                user = entityManager.merge(user);
            }
            return user;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save user: " + user, e);
        }
    }

    public void delete(Long id) {
        try {
            User user = findById(id);
            if (user != null) {
                entityManager.remove(user);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete User with ID: " + id, e);
        }
    }

    // Example additional method: find by username
    public User findByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find user by username: " + username, e);
        }
    }
}
