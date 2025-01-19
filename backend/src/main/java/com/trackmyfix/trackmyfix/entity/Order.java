package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "`order`")
@Data
@AllArgsConstructor


public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(nullable = false, unique = true, length = 25)
    private String number;

    @Column(columnDefinition = "TEXT")
    private String Observations;

    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    private Client client;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
