package com.sergeygolstinin.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "tables")
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    public DiningTable() {
    }

    public DiningTable(Integer number, Integer seats) {
        this.number = number;
        this.seats = seats;
    }

    // Getters and setters
}
