package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IOrderService {
    Map<String, Object> findAll();
    Order findByNumber(String number);
    Order findById(Long id);
    Order createOrder(OrderRequest orderRequest);
    void setActiveOrder(Long id,boolean active);
    Order updateOrder(Long id, OrderUpdateRequest orderUpdateRequest);
}
