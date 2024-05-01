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
}
