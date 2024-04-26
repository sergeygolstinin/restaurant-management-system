package com.sergeygolstinin.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @Column(name = "reservation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;

    public Reservation() {
    }

    public Reservation(User user, Table table, Date reservationTime) {
        this.user = user;
        this.table = table;
        this.reservationTime = reservationTime;
    }

    // Getters and setters
}
