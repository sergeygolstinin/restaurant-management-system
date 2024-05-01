package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MenuItemRepository {
    private EntityManager entityManager;

    public MenuItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MenuItem findById(Long id) {
        try {
            return entityManager.find(MenuItem.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find MenuItem with ID: " + id, e);
        }
    }

    public List<MenuItem> findAll() {
        try {
            TypedQuery<MenuItem> query = entityManager.createQuery("SELECT m FROM MenuItem m", MenuItem.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all menu items", e);
        }
    }

    public List<MenuItem> findByCategory(String category) {
        try {
            TypedQuery<MenuItem> query = entityManager.createQuery(
                "SELECT m FROM MenuItem m WHERE m.category = :category", MenuItem.class);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find menu items by category: " + category, e);
        }
    }

    public MenuItem save(MenuItem menuItem) {
        try {
            if (menuItem.getId() == null) {
                entityManager.persist(menuItem);
            } else {
                menuItem = entityManager.merge(menuItem);
            }
            return menuItem;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save menu item: " + menuItem, e);
        }
    }

    public void delete(Long id) {
        try {
            MenuItem menuItem = findById(id);
            if (menuItem != null) {
                entityManager.remove(menuItem);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete MenuItem with ID: " + id, e);
        }
    }
}
