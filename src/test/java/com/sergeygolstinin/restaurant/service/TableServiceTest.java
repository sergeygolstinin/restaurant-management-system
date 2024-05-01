package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.TableRepository;
import com.sergeygolstinin.restaurant.model.DiningTable;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TableServiceTest {
    @Mock
    private TableRepository tableRepository;
    
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TableService tableService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTable() {
        DiningTable table = new DiningTable(); // Set properties as needed
        when(tableRepository.save(any(DiningTable.class))).thenReturn(table);

        DiningTable created = tableService.createTable(table);
        assertNotNull(created);
        verify(tableRepository).save(table);
    }

    @Test
    void testGetTableById() {
        Long tableId = 1L;
        when(tableRepository.findById(tableId)).thenReturn(new DiningTable());
        
        DiningTable found = tableService.getTableById(tableId);
        assertNotNull(found);
        verify(tableRepository).findById(tableId);
    }
}
