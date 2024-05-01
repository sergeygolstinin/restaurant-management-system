package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class ReservationRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationRepository = new ReservationRepository(entityManager);
    }

    @Test
    void testFindById() {
        Reservation reservation = new Reservation();
        when(entityManager.find(Reservation.class, 1L)).thenReturn(reservation);

        Reservation found = reservationRepository.findById(1L);
        verify(entityManager).find(Reservation.class, 1L);
        assertSame(reservation, found);
    }

    @Test
    void testSave() {
        Reservation reservation = new Reservation();
        doNothing().when(entityManager).persist(reservation);

        Reservation saved = reservationRepository.save(reservation);
        verify(entityManager).persist(reservation);
        assertSame(reservation, saved);
    }

    @Test
    void testDelete() {
        Reservation reservation = new Reservation();
        when(entityManager.find(Reservation.class, 1L)).thenReturn(reservation);
        doNothing().when(entityManager).remove(reservation);

        reservationRepository.delete(1L);
        verify(entityManager).remove(reservation);
    }
}
