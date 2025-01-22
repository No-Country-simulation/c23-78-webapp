package com.trackmyfix.trackmyfix.Dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotBlank(message = "DNI is mandatory")
    @Pattern(regexp = "\\d{7,8}", message = "DNI must be 7 or 8 digits")
    private String dni;

    //tarifa de diagnóstico
    @NotNull(message = "Initial price is mandatory")
   // @DecimalMin(value = "0.0", inclusive = false, message = "Initial price must be greater than zero")
    private BigDecimal initialPrice;

    @NotBlank(message = "Observations are mandatory")
    @Size(min = 10, message = "Observations must have at least 10 characters")
    private String observations;
}
