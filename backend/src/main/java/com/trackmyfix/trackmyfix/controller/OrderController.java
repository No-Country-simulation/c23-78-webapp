package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.services.Impl.OrderService;
import lombok.AllArgsConstructor;
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
        return orderService.findAll();
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Order> findOrderByNumber(@PathVariable String number) {
        return orderService.findByNumber(number);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Order>createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequest orderUpdateRequest){
    return this.orderService.updateOrder(id,orderUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }

}
