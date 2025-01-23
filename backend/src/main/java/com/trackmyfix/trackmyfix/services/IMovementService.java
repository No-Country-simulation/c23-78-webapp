package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.entity.Movement;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IMovementService {
    ResponseEntity<Map<String,Object>> findAll();
    ResponseEntity<Movement> findByNumber(String number);
    ResponseEntity<Movement> findById(Long id);
    ResponseEntity<Movement> createOrder(OrderRequest orderRequest);
}
