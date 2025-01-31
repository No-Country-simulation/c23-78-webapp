package com.trackmyfix.trackmyfix.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String dni;
    private BigDecimal initialPrice;
    private String observations;
}
