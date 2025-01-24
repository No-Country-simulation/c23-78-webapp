package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @NotBlank(message = "The work order number is mandatory")
    @Column(nullable = false, unique = true, length = 25)
    private String number;

    @Column(columnDefinition = "TEXT")
    private String observations;

    //tarifa de diagnóstico
    @NotNull(message = "Initial price is mandatory")
    @DecimalMin(value = "10.0", inclusive = false, message = "Initial price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    //@DecimalMin(value = "0", inclusive = false, message = "Final price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    private Client client;

    @Column(nullable = false)
    private Boolean active;

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
