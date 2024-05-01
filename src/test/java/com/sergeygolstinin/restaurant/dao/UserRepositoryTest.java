package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRepository = new UserRepository(entityManager);
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setId(1L);
        when(entityManager.find(User.class, 1L)).thenReturn(user);

        User found = userRepository.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");
        user.setRole("customer");

        doNothing().when(entityManager).persist(user);
        userRepository.save(user);
        verify(entityManager).persist(user);
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setId(1L);

        when(entityManager.find(User.class, 1L)).thenReturn(user);
        doNothing().when(entityManager).remove(user);

        userRepository.delete(1L);
        verify(entityManager).remove(user);
    }
}
