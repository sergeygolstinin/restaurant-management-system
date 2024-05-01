package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.DiningTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TableRepository {
    private EntityManager entityManager;

    public TableRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public DiningTable findById(Long id) {
        try {
            return entityManager.find(DiningTable.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find Dining Table with ID: " + id, e);
        }
    }

    public List<DiningTable> findAll() {
        try {
            TypedQuery<DiningTable> query = entityManager.createQuery("SELECT dt FROM DiningTable dt", DiningTable.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all dining tables", e);
        }
    }

    public DiningTable save(DiningTable table) {
        try {
            if (table.getId() == null) {
                entityManager.persist(table);
            } else {
                table = entityManager.merge(table);
            }
            return table;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save dining table: " + table, e);
        }
    }

    public void delete(Long id) {
        try {
            DiningTable table = findById(id);
            if (table != null) {
                entityManager.remove(table);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete Dining Table with ID: " + id, e);
        }
    }

    // Example additional method: find by number of seats
    public List<DiningTable> findBySeats(int seats) {
        try {
            TypedQuery<DiningTable> query = entityManager.createQuery(
                "SELECT dt FROM DiningTable dt WHERE dt.seats = :seats", DiningTable.class);
            query.setParameter("seats", seats);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find dining tables with seats: " + seats, e);
        }
    }
}
