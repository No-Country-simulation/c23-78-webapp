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

    @NotBlank(message = "{device.model.mandatory}")
    @Size(max = 100, message = "{device.model.max_length}")
    @Column(nullable = false, length = 100)
    private String model;

    @NotBlank(message = "{device.serial_number.mandatory}")
    @Size(max = 100, message = "{device.serial_number.max_length}")
    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Size(max = 250, message = "{device.accessories.max_length}")
    @Column(length = 250)
    private String accessories;

    @NotNull(message = "{device.initial_price.mandatory}")
    @DecimalMin(value = "10.0", inclusive = false, message = "{device.initial_price.min_length}")
    @PositiveOrZero(message = "{device.initial_price.positive_or_zero}")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    @PositiveOrZero(message = "{device.final_price.positive_or_zero}")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Size(max = 500, message = "{device.client_description.max_length}")
    @Column(columnDefinition = "TEXT")
    private String clientDescription;

    @Size(max = 1000, message = "{device.technician_report.max_length}")
    @Column(columnDefinition = "TEXT")
    private String technicalReport;

    @NotNull(message = "{device.order.mandatory}")
    @JoinColumn(name = "id_order", nullable=false)
    @JsonBackReference
    @ManyToOne
    private Order order;

    @NotNull(message = "{device.type.mandatory}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotNull(message = "{device.state.mandatory}")
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