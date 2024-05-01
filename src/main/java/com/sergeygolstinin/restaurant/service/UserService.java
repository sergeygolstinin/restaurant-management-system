package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.UserRepository;
import com.sergeygolstinin.restaurant.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserService {
    private UserRepository userRepository;
    private EntityManager entityManager;

    public UserService(EntityManager entityManager, UserRepository userRepository) {
        
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        entityManager.getTransaction().begin();
        User user = userRepository.findById(id);
        entityManager.getTransaction().commit();
        return user;
    }

    public User createUser(User user) {
        entityManager.getTransaction().begin();
        userRepository.save(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public void deleteUser(Long id) {
        entityManager.getTransaction().begin();
        userRepository.delete(id);
        entityManager.getTransaction().commit();
    }

    // Additional methods as needed
}
