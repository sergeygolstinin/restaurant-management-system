package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.ReservationRepository;
import com.sergeygolstinin.restaurant.model.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private EntityManager entityManager;

    public ReservationService(EntityManager entityManager, ReservationRepository reservationRepository) {
        this.entityManager = entityManager;
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationById(Long id) {
        entityManager.getTransaction().begin();
        Reservation reservation = reservationRepository.findById(id);
        entityManager.getTransaction().commit();
        return reservation;
    }

    public Reservation createReservation(Reservation reservation) {
        entityManager.getTransaction().begin();
        Reservation savedReservation = reservationRepository.save(reservation);
        entityManager.getTransaction().commit();
        return savedReservation;
    }

    public void deleteReservation(Long id) {
        entityManager.getTransaction().begin();
        reservationRepository.delete(id);
        entityManager.getTransaction().commit();
    }

    public Reservation updateReservation(Reservation reservation) {
        entityManager.getTransaction().begin();
        Reservation updatedReservation = reservationRepository.save(reservation);
        entityManager.getTransaction().commit();
        return updatedReservation;
    }
}
