package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.MenuItemRepository;
import com.sergeygolstinin.restaurant.model.MenuItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenuItemService {
    private MenuItemRepository menuItemRepository;
    private EntityManager entityManager;

    public MenuItemService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantPU");
        this.entityManager = emf.createEntityManager();
        this.menuItemRepository = new MenuItemRepository(entityManager);
    }

    public MenuItem getMenuItemById(Long id) {
        entityManager.getTransaction().begin();
        MenuItem menuItem = menuItemRepository.findById(id);
        entityManager.getTransaction().commit();
        return menuItem;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        entityManager.getTransaction().begin();
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        entityManager.getTransaction().commit();
        return savedMenuItem;
    }

    public void updateMenuItem(MenuItem menuItem) {
        entityManager.getTransaction().begin();
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        entityManager.getTransaction().commit();
    }

    public void deleteMenuItem(Long id) {
        entityManager.getTransaction().begin();
        menuItemRepository.delete(id);
        entityManager.getTransaction().commit();
    }
}
