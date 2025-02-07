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
    @DecimalMin(value = "1000", message = "{device.initial_price.min_length}")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    @DecimalMin(value = "0.0", message = "{device.final_price.positive_or_zero}")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Size(max = 500, message = "{device.client_description.max_length}")
    @Column(columnDefinition = "TEXT")
    private String clientDescription;

    @Size(max = 1000, message = "{device.technician_report.max_length}")
    @Column(columnDefinition = "TEXT")
    private String technicalReport;

    @NotNull(message = "{device.type.mandatory}")
    private Type type;

    @NotNull(message = "{device.state.mandatory}")
    private State state;

    @JsonBackReference
    @NotNull(message = "device.order.mandatory")
    private OrderRequest order;

}
