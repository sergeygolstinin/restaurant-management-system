package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.MenuItemRepository;
import com.sergeygolstinin.restaurant.model.MenuItem;
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

    @InjectMocks
    private MenuItemService menuItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMenuItem() {
        MenuItem menuItem = new MenuItem("Pizza", 9.99, "Delicious cheese pizza");
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem created = menuItemService.createMenuItem(menuItem);
        assertNotNull(created);
        assertEquals("Pizza", created.getName());
        verify(menuItemRepository).save(menuItem);
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
    }

    @Test
    void testDeleteMenuItem() {
        Long menuItemId = 1L;
        doNothing().when(menuItemRepository).delete(menuItemId);
        
        menuItemService.deleteMenuItem(menuItemId);
        verify(menuItemRepository).delete(menuItemId);
    }
}
