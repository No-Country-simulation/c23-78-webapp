package com.trackmyfix.trackmyfix.Dto.Request;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeviceRequestDTO {
    @NotBlank(message = "Debe de colocar el modelo del dispositivo")
    @NotNull
    private String model;

    @NotBlank(message = "Debe de colocar el numero de serial del dispositivo")
    @NotNull
    private String serialNumber;

    private String accessories;

    @NotNull(message = "Initial price is mandatory")
    @DecimalMin(value = "10.0", inclusive = false, message = "Initial price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal initialPrice;

    //@DecimalMin(value = "0", inclusive = false, message = "Final price must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal finalPrice;

    private String clientDescription;

    private String technicalReport;

    @NotNull(message = "Debes de colocar un tipo de dispositivo")
    private Type type;

    @NotNull(message = "Debes de colocar un estado")
    private State state;
}
