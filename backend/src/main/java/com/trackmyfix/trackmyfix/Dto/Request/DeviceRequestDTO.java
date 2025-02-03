package com.trackmyfix.trackmyfix.Dto.Request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDTO {

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
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    @DecimalMin(value = "0.0", inclusive = true, message = "Final price must be positive")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Size(max = 500, message = "Client description cannot exceed 500 characters")
    @Column(columnDefinition = "TEXT")
    private String clientDescription;

    @Size(max = 1000, message = "Technical report cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String technicalReport;

    @NotNull(message = "You must specify a state")
    private Type type;

    @NotNull(message = "You must specify a state")
    private State state;

    @JsonBackReference
    @NotNull(message = "Order cannot be null")
    private OrderRequest order;

}
