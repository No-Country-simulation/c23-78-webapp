package com.trackmyfix.trackmyfix.utils;

import com.trackmyfix.trackmyfix.entity.Order;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class MovementUtils {
    public String generateDescription(Order order, Map<String, Object> changes) {
        String changesDescription = (changes != null) ? changes.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ")) : "";
        return  (changesDescription.isEmpty() ? "" : " Cambios: [" + changesDescription + "]");
    }
}
