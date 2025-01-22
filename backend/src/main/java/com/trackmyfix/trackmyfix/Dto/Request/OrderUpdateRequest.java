package com.trackmyfix.trackmyfix.Dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {

    @NotBlank(message = "Observations are mandatory")
    @Size(min = 10, message = "Observations must have at least 10 characters")
    private String observations;

    //tarifa de diagnóstico
    @NotNull(message = "Initial price is mandatory")
    //@DecimalMin(value = "0.0", inclusive = false, message = "Initial price must be greater than zero")
    private BigDecimal initialPrice;

    @NotNull(message = "Final price is mandatory")
    //@DecimalMin(value = "0.0", inclusive = false, message = "Final price must be greater than zero")
    private BigDecimal finalPrice;
}
