package com.trackmyfix.trackmyfix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Model is mandatory")
    @Size(max = 100, message = "Model cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String model;

    @NotBlank(message = "Serial number is mandatory")
    @Size(max = 100, message = "Serial number cannot exceed 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Size(max = 250, message = "Accessories cannot exceed 250 characters")
    @Column(length = 250)
    private String accessories;

    @NotNull(message = "Initial price is mandatory")
    @DecimalMin(value = "10.0", inclusive = false, message = "Initial price must be greater than zero")
    @PositiveOrZero(message = "Value must be positive or zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    @PositiveOrZero(message = "Value must be positive or zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Size(max = 500, message = "Client description cannot exceed 500 characters")
    @Column(columnDefinition = "TEXT")
    private String clientDescription;

    @Size(max = 1000, message = "Technical report cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String technicalReport;

    @NotNull(message = "Order is mandatory")
    @JoinColumn(name = "id_order", nullable=false)
    @JsonBackReference
    @ManyToOne
    private Order order;

    @NotNull(message = "State is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotNull(message = "State is mandatory")
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