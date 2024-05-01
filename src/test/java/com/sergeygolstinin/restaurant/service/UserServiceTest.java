package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.UserRepository;
import com.sergeygolstinin.restaurant.model.User;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("john_doe", "password123", "customer");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User created = userService.createUser(user);
        assertNotNull(created);
        assertEquals("john_doe", created.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User("john_doe", "password123", "customer");
        when(userRepository.findById(userId)).thenReturn(user);

        User found = userService.getUserById(userId);
        assertNotNull(found);
        assertEquals("john_doe", found.getUsername());
        verify(userRepository).findById(userId);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).delete(userId);

        userService.deleteUser(userId);
        verify(userRepository).delete(userId);
    }
}
