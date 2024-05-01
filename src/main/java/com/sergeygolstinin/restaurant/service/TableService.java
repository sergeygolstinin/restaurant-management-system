package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.TableRepository;
import com.sergeygolstinin.restaurant.model.DiningTable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TableService {
    private  TableRepository tableRepository;
    private  EntityManager entityManager;

    public TableService(EntityManager entityManager, TableRepository tableRepository) {
        this.entityManager = entityManager;
        this.tableRepository = tableRepository;
    }

    public DiningTable getTableById(Long id) {
        entityManager.getTransaction().begin();
        DiningTable table = tableRepository.findById(id);
        entityManager.getTransaction().commit();
        return table;
    }

    public DiningTable createTable(DiningTable table) {
        entityManager.getTransaction().begin();
        DiningTable savedTable = tableRepository.save(table);
        entityManager.getTransaction().commit();
        return savedTable;
    }

    public void deleteTable(Long id) {
        entityManager.getTransaction().begin();
        tableRepository.delete(id);
        entityManager.getTransaction().commit();
    }

    public DiningTable updateTable(DiningTable table) {
        entityManager.getTransaction().begin();
        DiningTable updatedTable = tableRepository.save(table);
        entityManager.getTransaction().commit();
        return updatedTable;
    }
}
