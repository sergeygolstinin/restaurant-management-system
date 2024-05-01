package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.TableRepository;
import com.sergeygolstinin.restaurant.model.DiningTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableService {
    private static final Logger log = LoggerFactory.getLogger(TableService.class);
    private final TableRepository tableRepository;
    private final EntityManager entityManager;

    public TableService(EntityManager entityManager, TableRepository tableRepository) {
        this.entityManager = entityManager;
        this.tableRepository = tableRepository;
    }

    public DiningTable getTableById(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            DiningTable table = tableRepository.findById(id);
            tx.commit();
            return table;
        } catch (Exception e) {
            tx.rollback();
            log.error("Failed to retrieve table by ID: {}", id, e);
            throw new RuntimeException("Transaction failed", e);
        }
    }

    public DiningTable createTable(DiningTable table) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            DiningTable savedTable = tableRepository.save(table);
            tx.commit();
            return savedTable;
        } catch (Exception e) {
            tx.rollback();
            log.error("Failed to create table: {}", table, e);
            throw new RuntimeException("Transaction failed", e);
        }
    }

    public void deleteTable(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            tableRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Failed to delete table with ID: {}", id, e);
            throw new RuntimeException("Transaction failed", e);
        }
    }

    public DiningTable updateTable(DiningTable table) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            DiningTable updatedTable = tableRepository.save(table);
            tx.commit();
            return updatedTable;
        } catch (Exception e) {
            tx.rollback();
            log.error("Failed to update table: {}", table, e);
            throw new RuntimeException("Transaction failed", e);
        }
    }
}
