package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.event.DeviceEvent;
import com.trackmyfix.trackmyfix.event.OrderEvent;
import com.trackmyfix.trackmyfix.exceptions.InvalidPriceException;
import com.trackmyfix.trackmyfix.exceptions.OrderNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.*;
import com.trackmyfix.trackmyfix.services.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ApplicationEventPublisher eventPublisher;

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
        Order order = orderRepository.findByNumber(number)
                .orElseThrow(() -> new OrderNotFoundException("Orden con número " + number + " no encontrada"));
        return ResponseEntity.ok(order);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Order> findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));
        return ResponseEntity.ok(order);
    }

    @Override
    @Transactional
    public ResponseEntity<Order> createOrder(OrderRequest orderRequest) {
        Client client = clientRepository.findByDni(orderRequest.getDni()).orElseThrow(
                () -> new UserNotFoundException("Cliente con DNI " + orderRequest.getDni() + " no encontrado"));

        this.validatePrices(orderRequest.getInitialPrice(), BigDecimal.ZERO);

        Order newOrder = Order.builder().number(generateOrderNumber()).observations(orderRequest.getObservations())
                .initialPrice(orderRequest.getInitialPrice()).finalPrice(BigDecimal.ZERO).client(client).active(true)
                .build();

        Order savedOrder = orderRepository.save(newOrder);
        eventPublisher.publishEvent(new DeviceEvent(savedOrder.getDevices()));
        eventPublisher.publishEvent(new OrderEvent(savedOrder, Action.CREO_ORDEN_TRABAJO));


        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deactivateOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));

        if (!order.getActive()) {
            throw new IllegalStateException("La orden ya está inactiva");
        }

        order.setActive(false);
        orderRepository.save(order);

        eventPublisher.publishEvent(new OrderEvent(order, Action.ELIMINO_ORDEN_TRABAJO));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @Transactional
    public ResponseEntity<Order> updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
<<<<<<< HEAD

        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));
=======
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));
>>>>>>> developer-backend-esteban

        if(!existingOrder.getActive()){
            throw  new IllegalStateException("No se puede actualizar una orden inactiva");
        }

        this.validatePrices(orderUpdateRequest.getInitialPrice(), orderUpdateRequest.getFinalPrice());

        Map<String, Object> changes = this.detectChanges(existingOrder, orderUpdateRequest);

        existingOrder.setObservations(orderUpdateRequest.getObservations());
        existingOrder.setInitialPrice(orderUpdateRequest.getInitialPrice());
        existingOrder.setFinalPrice(orderUpdateRequest.getFinalPrice());

        Order updatedOrder = orderRepository.save(existingOrder);

        eventPublisher.publishEvent(new OrderEvent(updatedOrder, Action.MODIFICO_ORDEN_TRABAJO, changes));

        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    private void validatePrices(BigDecimal initialPrice, BigDecimal finalPrice) {
        if (initialPrice.compareTo(BigDecimal.TEN) < 0) {
            throw new InvalidPriceException("El precio inicial no puede ser menor a 10.");
        }
        if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException("El precio final no puede ser negativo.");
        }
    }


    private String generateOrderNumber() {
        String lastOrderNumber = getLastOrderNumber();
        int lastNumber = lastOrderNumber != null ? extractNumberFromOrder(lastOrderNumber) : 0;
        int newOrderNumber = lastNumber + 1;
        return formatOrderNumber(newOrderNumber);
    }

    private String getLastOrderNumber() {
        return orderRepository.findTopByOrderByIdOrderDesc()
                .map(Order::getNumber)
                .orElse(null);
    }

    private int extractNumberFromOrder(String orderNumber) {
        return Integer.parseInt(orderNumber.replace("ORD-", ""));
    }

    private String formatOrderNumber(int number) {
        return "ORD-" + String.format("%05d", number);
    }

    private Map<String, Object> detectChanges(Order existingOrder, OrderUpdateRequest orderUpdateRequest) {
        Map<String, Object> changes = new HashMap<>();

        if (!existingOrder.getObservations().equals(orderUpdateRequest.getObservations())) {
            changes.put("Observaciones", "De: [" + existingOrder.getObservations() + "] a: [" + orderUpdateRequest.getObservations() + "]");
        }
        if (!existingOrder.getInitialPrice().equals(orderUpdateRequest.getInitialPrice())) {
            changes.put("Precio Inicial", "De: [" + existingOrder.getInitialPrice() + "] a: [" + orderUpdateRequest.getInitialPrice() + "]");
        }
        if (!existingOrder.getFinalPrice().equals(orderUpdateRequest.getFinalPrice())) {
            changes.put("Precio Final", "De: [" + existingOrder.getFinalPrice() + "] a: [" + orderUpdateRequest.getFinalPrice() + "]");
        }

        return changes;
    }


}
