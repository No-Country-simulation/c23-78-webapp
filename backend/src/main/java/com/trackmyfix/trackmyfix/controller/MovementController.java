package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Movement;
import com.trackmyfix.trackmyfix.services.Impl.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movement")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllMovements() {
        Map<String, Object> response = movementService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> findById(@PathVariable Long id) {
        Movement movement = movementService.findById(id);
        return ResponseEntity.ok(movement);
    }

    @GetMapping("/action/{action}")
    public ResponseEntity<List<Movement>> findByAction(@PathVariable Action action) {
        List<Movement> movements = movementService.findByAction(action);
        return ResponseEntity.ok(movements);
    }

    @GetMapping("/actions")
    public ResponseEntity<List<String>> getAllActions() {
        List<String> actions = movementService.getAllAction();
        return ResponseEntity.ok(actions);
    }
}
