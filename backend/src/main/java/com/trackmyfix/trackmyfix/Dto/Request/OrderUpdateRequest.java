package com.trackmyfix.trackmyfix.Dto.Request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {

    @Size(max = 65535, message = "Observations cannot exceed 65535 characters")
    @Column(columnDefinition = "TEXT")
    private String observations;

    @Size(min = 1, max = 1, message = "There must be exactly one device")
    @JsonManagedReference
    private List<DeviceRequestDTO> devices = new ArrayList<>();
}
