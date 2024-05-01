package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.ReservationRepository;
import com.sergeygolstinin.restaurant.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation(); // Assume required properties are set
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation created = reservationService.createReservation(reservation);
        assertNotNull(created);
        verify(reservationRepository).save(reservation);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }

    @Test
    void testGetReservationById() {
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(new Reservation());
        
        Reservation found = reservationService.getReservationById(reservationId);
        assertNotNull(found);
        verify(reservationRepository).findById(reservationId);
    }
        @Test
        void testDeleteReservation() {
            Long reservationId = 1L;
            doNothing().when(reservationRepository).delete(reservationId);

            reservationService.deleteReservation(reservationId);

            verify(reservationRepository).delete(reservationId);
            verify(entityTransaction).begin();
            verify(entityTransaction).commit();
        }

        @Test
        void testUpdateReservation() {
            Reservation reservation = new Reservation();
            reservation.setId(1L); // Assuming ID is set for updating
            when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

            Reservation updated = reservationService.updateReservation(reservation);
            assertNotNull(updated);
            verify(reservationRepository).save(reservation);
            verify(entityTransaction).begin();
            verify(entityTransaction).commit();
        }

        @Test
        void testDeleteReservationThrowsException() {
            Long reservationId = 1L;
            doThrow(new RuntimeException("Database error")).when(reservationRepository).delete(reservationId);

            assertThrows(RuntimeException.class, () -> reservationService.deleteReservation(reservationId));

            verify(entityTransaction).begin();
            verify(entityTransaction).rollback(); // Ensure rollback on exception
        }

        @Test
        void testUpdateReservationThrowsException() {
            Reservation reservation = new Reservation();
            reservation.setId(1L); // Assume ID is set for an update
            when(reservationRepository.save(any(Reservation.class))).thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () -> reservationService.updateReservation(reservation));

            verify(entityTransaction).begin();
            verify(entityTransaction).rollback(); // Ensure rollback on exception
        }
    }