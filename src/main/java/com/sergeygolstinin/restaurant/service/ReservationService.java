package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.ReservationRepository;
import com.sergeygolstinin.restaurant.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationService {
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    private ReservationRepository reservationRepository;
    private EntityManager entityManager;

    public ReservationService(EntityManager entityManager, ReservationRepository reservationRepository) {
        this.entityManager = entityManager;
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationById(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Reservation reservation = reservationRepository.findById(id);
            tx.commit();
            return reservation;
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to get reservation by ID: {}", id, e);
            throw new IllegalStateException("Transaction failed", e);
        }
    }

    public Reservation createReservation(Reservation reservation) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Reservation savedReservation = reservationRepository.save(reservation);
            tx.commit();
            return savedReservation;
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to create reservation: {}", reservation, e);
            throw new IllegalStateException("Transaction failed", e);
        }
    }
    public Reservation updateReservation(Reservation reservation) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Reservation updatedReservation = reservationRepository.save(reservation);
            tx.commit();
            return updatedReservation;
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to update reservation: {}", reservation, e);
            throw new IllegalStateException("Transaction failed", e);
        }
    }

    public void deleteReservation(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            reservationRepository.delete(id);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error("Failed to delete reservation with ID: {}", id, e);
            throw new IllegalStateException("Transaction failed", e);
        }
    }
}