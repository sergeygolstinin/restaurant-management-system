package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Reservation;

import jakarta.persistence.EntityManager;

public class ReservationRepository {
    private EntityManager entityManager;

    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    public Reservation save(Reservation reservation) {
        entityManager.persist(reservation);
        return reservation;
    }

    public void delete(Long id) {
        Reservation reservation = findById(id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }

    // Additional methods as needed
}
