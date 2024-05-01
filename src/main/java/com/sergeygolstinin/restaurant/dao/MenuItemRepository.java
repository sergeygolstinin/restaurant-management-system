package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.MenuItem;

import jakarta.persistence.EntityManager;

public class MenuItemRepository {
    private EntityManager entityManager;

    public MenuItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MenuItem findById(Long id) {
        return entityManager.find(MenuItem.class, id);
    }

    public MenuItem save(MenuItem menuItem) {
        entityManager.persist(menuItem);
        return menuItem;
    }

    public void delete(Long id) {
        MenuItem menuItem = findById(id);
        if (menuItem != null) {
            entityManager.remove(menuItem);
        }
    }

    // Additional methods as needed
}
