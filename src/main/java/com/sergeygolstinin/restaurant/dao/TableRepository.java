package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.DiningTable;

import jakarta.persistence.EntityManager;

public class TableRepository {
    private EntityManager entityManager;

    public TableRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public DiningTable findById(Long id) {
        return entityManager.find(DiningTable.class, id);
    }

    public DiningTable save(DiningTable table) {
        entityManager.persist(table);
        return table;
    }

    public void delete(Long id) {
        DiningTable table = findById(id);
        if (table != null) {
            entityManager.remove(table);
        }
    }

    // Additional methods as needed
}
