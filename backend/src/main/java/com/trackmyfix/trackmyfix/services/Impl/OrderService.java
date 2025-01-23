package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.exceptions.InvalidPriceException;
import com.trackmyfix.trackmyfix.exceptions.OrderNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.ResourceNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.*;
import com.trackmyfix.trackmyfix.services.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TechnicianRepository technicianRepository;
    private final ActionRepository actionRepository;
    private final MovementRepository movementRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Order> orders = (List<Order>) orderRepository.findAll();
        response.put("orders", orders);
        response.put("orderSize", orders.size());
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Order> findByNumber(String number) {
        Order order = orderRepository.findByNumber(number).orElseThrow(() -> new OrderNotFoundException("Orden con número " + number + " no encontrada"));
        return ResponseEntity.ok(order);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Order> findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));
        return ResponseEntity.ok(order);
    }

    @Override
    @Transactional
    public ResponseEntity<Order> createOrder(OrderRequest orderRequest) {
        Client client = clientRepository.findByDni(orderRequest.getDni()).orElseThrow(() -> new UserNotFoundException("Cliente con DNI " + orderRequest.getDni() + " no encontrado"));

        this.validatePrices(orderRequest.getInitialPrice(), BigDecimal.ZERO);

        Order newOrder = Order.builder().number(generateOrderNumber()).observations(orderRequest.getObservations()).initialPrice(orderRequest.getInitialPrice()).finalPrice(BigDecimal.ZERO).client(client).build();

        Order savedOrder = orderRepository.save(newOrder);

        // Crear el movimiento asociado con la orden
        this.createMovement(savedOrder, "Movimiento inicial para la orden " + savedOrder.getNumber());

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }


    @Override
    @Transactional
    public ResponseEntity<Void> deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    @Transactional
    public ResponseEntity<Order> updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));

        this.validatePrices(orderUpdateRequest.getInitialPrice(), orderUpdateRequest.getFinalPrice());

        existingOrder.setObservations(orderUpdateRequest.getObservations());
        existingOrder.setInitialPrice(orderUpdateRequest.getInitialPrice());
        existingOrder.setFinalPrice(orderUpdateRequest.getFinalPrice());

        Order updatedOrder = orderRepository.save(existingOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    private void validatePrices(BigDecimal initialPrice, BigDecimal finalPrice) {
        if (false) {
            throw new InvalidPriceException("El precio inicial y final no pueden ser menores a 10.");
        }
    }

    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis();
    }

    private void createMovement(Order order, String description) {
        Movement movement = new Movement();
        movement.setOrder(order);
        movement.setDescription(description);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Technician technician = technicianRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Técnico con email " + email + " no encontrado"));

        Action action = actionRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Acción con ID " + 1 + " no encontrada"));


        movement.setAction(action);
        movement.setTechnician(technician);

        movementRepository.save(movement);
    }

}
