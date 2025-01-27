package com.trackmyfix.trackmyfix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private String accessories;

    @NotNull(message = "Initial price is mandatory")
    @DecimalMin(value = "10.0", inclusive = false, message = "Initial price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    //@DecimalMin(value = "0", inclusive = false, message = "Final price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Column(columnDefinition = "TEXT")
    private String clientDescription; // Problema reportado por el cliente

    @Column(columnDefinition = "TEXT")
    private String technicalReport; // Análisis técnico y diagnóstico

    @ManyToOne
    @JoinColumn(name = "id_order", nullable=false)
    @JsonBackReference
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

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