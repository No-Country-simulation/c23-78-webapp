package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Movement;
import com.trackmyfix.trackmyfix.entity.State;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IMovementService {
    Map<String, Object> findAll();
    List<Movement> findByAction(Action action);
    Movement findById(Long id);
}
