package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IOrderService {
    ResponseEntity<Map<String,Object>>findAll();
    ResponseEntity<Order> findByNumber(String number);
    ResponseEntity<Order> findById(Long id);
    Order createOrder(OrderRequest orderRequest);
    ResponseEntity<Void>deactivateOrder(Long id);
    ResponseEntity<Order> updateOrder(Long id,OrderUpdateRequest orderUpdateRequest);
}
