package com.sergeygolstinin.restaurant.service;

import com.sergeygolstinin.restaurant.dao.TableRepository;
import com.sergeygolstinin.restaurant.model.DiningTable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private TableService tableService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    @Test
    void testCreateTable() {
        DiningTable table = new DiningTable(); // Assume necessary properties are set
        when(tableRepository.save(any(DiningTable.class))).thenReturn(table);

        DiningTable created = tableService.createTable(table);
        assertNotNull(created);
        verify(tableRepository).save(table);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }

    @Test
    void testGetTableById() {
        Long tableId = 1L;
        when(tableRepository.findById(tableId)).thenReturn(new DiningTable());

        DiningTable found = tableService.getTableById(tableId);
        assertNotNull(found);
        verify(tableRepository).findById(tableId);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }

    @Test
    void testDeleteTable() {
        Long tableId = 1L;
        doNothing().when(tableRepository).delete(tableId);

        tableService.deleteTable(tableId);
        
        verify(tableRepository).delete(tableId);
        verify(entityTransaction).begin();
        verify(entityTransaction).commit();
    }
    
    @Test
    void testCreateTableThrowsException() {
        DiningTable table = new DiningTable();
        when(tableRepository.save(any(DiningTable.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> tableService.createTable(table));

        verify(entityTransaction).begin();
        verify(entityTransaction).rollback();
    }

}
