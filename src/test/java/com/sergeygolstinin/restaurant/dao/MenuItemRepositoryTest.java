package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuItemRepository = new MenuItemRepository(entityManager);
    }

    @Test
    void testFindById() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);
        when(entityManager.find(MenuItem.class, 1L)).thenReturn(menuItem);

        MenuItem found = menuItemRepository.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testSave() {
        MenuItem menuItem = new MenuItem("Pizza", 9.99, "Delicious pizza");
        doNothing().when(entityManager).persist(menuItem);
        menuItemRepository.save(menuItem);
        verify(entityManager).persist(menuItem);
    }

    @Test
    void testDelete() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);

        when(entityManager.find(MenuItem.class, 1L)).thenReturn(menuItem);
        doNothing().when(entityManager).remove(menuItem);

        menuItemRepository.delete(1L);
        verify(entityManager).remove(menuItem);
    }
}
