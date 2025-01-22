package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import com.trackmyfix.trackmyfix.services.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Order> orders = (List<Order>) this.orderRepository.findAll();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        response.put("orders", orders);
        response.put("orderSize", orders.size());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Order> findByNumber(String number) {
        Order order = this.orderRepository.findByNumber(number).orElse(null);
        return order != null ? new ResponseEntity<>(order, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Order> findById(Long id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        return order != null ? new ResponseEntity<>(order, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<Order> createOrder(OrderRequest orderRequest) {
        Client client = clientRepository.findByDni(orderRequest.getDni()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Order newOrder = Order.builder()
                .number(generateOrderNumber())
                .observations(orderRequest.getObservations())
                .initialPrice(orderRequest.getInitialPrice())
                .finalPrice(BigDecimal.ZERO) // Precio final por defecto .client(client)
                .client(client)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        Order savedOrder = orderRepository.save(newOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteOrder(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Order> updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        existingOrder.setObservations(orderUpdateRequest.getObservations());
        existingOrder.setInitialPrice(orderUpdateRequest.getInitialPrice());
        existingOrder.setUpdatedAt(new Date());

        Order updatedOrder = orderRepository.save(existingOrder);
        return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
    }


    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis();
    }
}
