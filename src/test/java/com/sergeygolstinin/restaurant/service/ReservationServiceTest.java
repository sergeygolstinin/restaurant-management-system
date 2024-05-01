package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.ReservationRepository;
import com.sergeygolstinin.restaurant.model.Reservation;
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

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation(); // Set properties as needed
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation created = reservationService.createReservation(reservation);
        assertNotNull(created);
        verify(reservationRepository).save(reservation);
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
