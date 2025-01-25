package com.trackmyfix.trackmyfix.Dto.Request;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String accesories;

    private String clientDescription;

    private String technicalReport;

    private Order order;

    @NotNull(message = "Debes de colocar un tipo de dispositivo")
    private Type type;

    @NotNull(message = "Debes de colocar un estado")
    private State state;
}
