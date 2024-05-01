package com.sergeygolstinin.restaurant.dao;

import com.sergeygolstinin.restaurant.model.DiningTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class TableRepositoryTest {
    @Mock
    private EntityManager entityManager;

    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tableRepository = new TableRepository(entityManager);
    }

    @Test
    void testFindById() {
        DiningTable table = new DiningTable();
        when(entityManager.find(DiningTable.class, 1L)).thenReturn(table);

        DiningTable found = tableRepository.findById(1L);
        verify(entityManager).find(DiningTable.class, 1L);
        assertSame(table, found);
    }

    @Test
    void testSave() {
        DiningTable table = new DiningTable();
        doNothing().when(entityManager).persist(table);

        DiningTable saved = tableRepository.save(table);
        verify(entityManager).persist(table);
        assertSame(table, saved);
    }

    @Test
    void testDelete() {
        DiningTable table = new DiningTable();
        when(entityManager.find(DiningTable.class, 1L)).thenReturn(table);
        doNothing().when(entityManager).remove(table);

        tableRepository.delete(1L);
        verify(entityManager).remove(table);
    }
}
