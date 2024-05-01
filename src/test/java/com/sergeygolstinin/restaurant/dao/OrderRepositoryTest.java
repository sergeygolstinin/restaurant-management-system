package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderRepository = new OrderRepository(entityManager);
    }

    @Test
    void testFindById() {
        Order order = new Order();
        when(entityManager.find(Order.class, 1L)).thenReturn(order);

        Order found = orderRepository.findById(1L);
        verify(entityManager).find(Order.class, 1L);
        assertSame(order, found);
    }

    @Test
    void testSave() {
        Order order = new Order();
        doNothing().when(entityManager).persist(order);

        Order saved = orderRepository.save(order);
        verify(entityManager).persist(order);
        assertSame(order, saved);
    }

    @Test
    void testDelete() {
        Order order = new Order();
        when(entityManager.find(Order.class, 1L)).thenReturn(order);
        doNothing().when(entityManager).remove(order);

        orderRepository.delete(1L);
        verify(entityManager).remove(order);
    }
}
