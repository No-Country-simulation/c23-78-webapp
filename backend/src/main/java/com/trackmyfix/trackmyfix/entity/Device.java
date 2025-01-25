package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevice;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Column(length = 250)
    private String accesories;

    @Column(columnDefinition = "TEXT")
    private String clientDescription; // Problema reportado por el cliente

    @Column(columnDefinition = "TEXT")
    private String technicalReport; // Análisis técnico y diagnóstico
    // @ManyToOne
    // @JoinColumn(name = "id_type")
    // private Type type;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    // @ManyToOne
    // @JoinColumn(name = "id_state")
    // private State state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

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