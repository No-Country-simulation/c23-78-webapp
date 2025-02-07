package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.services.Impl.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/work-order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllOrders() {
        Map<String, Object> response = orderService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Order> findOrderByNumber(@PathVariable String number) {
        Order order = orderService.findByNumber(number);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderUpdateRequest orderUpdateRequest){
        Order updatedOrder = orderService.updateOrder(id, orderUpdateRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/{id}/set-active")
    public ResponseEntity<Void> setActiveOrder(@PathVariable Long id, @RequestBody Map<String, Boolean> requestBody) {
        boolean active = requestBody.get("active");
        orderService.setActiveOrder(id, active);
        return ResponseEntity.noContent().build();
    }
}
