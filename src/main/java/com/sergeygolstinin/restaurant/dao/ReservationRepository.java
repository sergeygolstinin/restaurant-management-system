package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ReservationRepository {
    private EntityManager entityManager;

    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Reservation findById(Long id) {
        try {
            return entityManager.find(Reservation.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find Reservation with ID: " + id, e);
        }
    }

    public List<Reservation> findAll() {
        try {
            TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to retrieve all reservations", e);
        }
    }

    public Reservation save(Reservation reservation) {
        try {
            if (reservation.getId() == null) {
                entityManager.persist(reservation);
            } else {
                reservation = entityManager.merge(reservation);
            }
            return reservation;
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save reservation: " + reservation, e);
        }
    }

    public void delete(Long id) {
        try {
            Reservation reservation = findById(id);
            if (reservation != null) {
                entityManager.remove(reservation);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete Reservation with ID: " + id, e);
        }
    }

    // Add method to find reservations by customer or other criteria
    public List<Reservation> findByCustomerName(String customerName) {
        try {
            TypedQuery<Reservation> query = entityManager.createQuery(
                "SELECT r FROM Reservation r WHERE r.customer.name = :customerName", Reservation.class);
            query.setParameter("customerName", customerName);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to find reservations for customer: " + customerName, e);
        }
    }
}
