package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.entity.Movement;
import com.trackmyfix.trackmyfix.entity.State;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IMovementService {
    ResponseEntity<Map<String,Object>> findAll();
    ResponseEntity<Movement> findByState(State state);
    ResponseEntity<Movement> findById(Long id);
    ResponseEntity<Movement> createOrder(OrderRequest orderRequest);
}
