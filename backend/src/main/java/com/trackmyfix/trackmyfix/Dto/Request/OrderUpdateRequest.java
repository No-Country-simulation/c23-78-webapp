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
}
