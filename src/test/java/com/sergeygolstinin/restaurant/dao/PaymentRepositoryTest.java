package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class PaymentRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRepository = new PaymentRepository(entityManager);
    }

    @Test
    void testFindById() {
        Payment payment = new Payment();
        when(entityManager.find(Payment.class, 1L)).thenReturn(payment);

        Payment found = paymentRepository.findById(1L);
        verify(entityManager).find(Payment.class, 1L);
        assertSame(payment, found);
    }

    @Test
    void testSave() {
        Payment payment = new Payment();
        doNothing().when(entityManager).persist(payment);

        Payment saved = paymentRepository.save(payment);
        verify(entityManager).persist(payment);
        assertSame(payment, saved);
    }

    @Test
    void testDelete() {
        Payment payment = new Payment();
        when(entityManager.find(Payment.class, 1L)).thenReturn(payment);
        doNothing().when(entityManager).remove(payment);

        paymentRepository.delete(1L);
        verify(entityManager).remove(payment);
    }
}
