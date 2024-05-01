package com.sergeygolstinin.restaurant.service;



import com.sergeygolstinin.restaurant.dao.MenuItemRepository;
import com.sergeygolstinin.restaurant.model.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemServiceTest {
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private MenuItemService menuItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    @Test
    void testCreateMenuItem() {
        MenuItem menuItem = new MenuItem("Pizza", 9.99, "Delicious cheese pizza");
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem created = menuItemService.createMenuItem(menuItem);
        assertNotNull(created);
        assertEquals("Pizza", created.getName());
        verify(menuItemRepository).save(menuItem);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }

    @Test
    void testGetMenuItemById() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem("Pizza", 9.99, "Delicious cheese pizza");
        when(menuItemRepository.findById(menuItemId)).thenReturn(menuItem);

        MenuItem found = menuItemService.getMenuItemById(menuItemId);
        assertNotNull(found);
        assertEquals("Pizza", found.getName());
        verify(menuItemRepository).findById(menuItemId);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }

    @Test
    void testDeleteMenuItem() {
        Long menuItemId = 1L;
        doNothing().when(menuItemRepository).delete(menuItemId);
        menuItemService.deleteMenuItem(menuItemId);
        verify(menuItemRepository).delete(menuItemId);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }
    @Test
    void testUpdateMenuItem() {
        MenuItem menuItem = new MenuItem("Burger", 11.99, "Deluxe Burger");
        menuItem.setId(2L);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        menuItemService.updateMenuItem(menuItem);

        verify(entityTransaction).begin();
        verify(menuItemRepository).save(menuItem);
        verify(entityTransaction).commit();
    }

    @Test
    void testUpdateMenuItemThrowsException() {
        MenuItem menuItem = new MenuItem("Burger", 11.99, "Deluxe Burger");
        menuItem.setId(2L);
        when(menuItemRepository.save(any(MenuItem.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> menuItemService.updateMenuItem(menuItem));

        verify(entityTransaction).begin();
        verify(entityTransaction).rollback(); // Ensure rollback on exception
    }
}