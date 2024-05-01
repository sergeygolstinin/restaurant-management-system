package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.MenuItemRepository;
import com.sergeygolstinin.restaurant.model.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final EntityManager entityManager;

    public MenuItemService(EntityManager entityManager, MenuItemRepository menuItemRepository) {
        this.entityManager = entityManager;  // Ensure entityManager is initialized
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem getMenuItemById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            MenuItem menuItem = menuItemRepository.findById(id);
            transaction.commit();
            return menuItem;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to get menu item by ID: " + id, e);
        }
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            MenuItem savedMenuItem = menuItemRepository.save(menuItem);
            transaction.commit();
            return savedMenuItem;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to create menu item: " + menuItem, e);
        }
    }

    public void updateMenuItem(MenuItem menuItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            menuItemRepository.save(menuItem);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to update menu item: " + menuItem, e);
        }
    }

    public void deleteMenuItem(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            menuItemRepository.delete(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to delete menu item with ID: " + id, e);
        }
    }
}
