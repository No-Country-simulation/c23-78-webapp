package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.event.DeviceCreateEvent;
import com.trackmyfix.trackmyfix.event.OrderCreateEvent;
import com.trackmyfix.trackmyfix.event.OrderUpdateEvent;
import com.trackmyfix.trackmyfix.exceptions.OrderNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.*;
import com.trackmyfix.trackmyfix.services.IOrderService;
import com.trackmyfix.trackmyfix.utils.OrderUtils;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final DeviceService deviceService;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return Map.of("orders", orders, "orderSize", orders.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Order findByNumber(String number) {
        return orderRepository.findByNumber(number)
                .orElseThrow(() -> new OrderNotFoundException("Order with number " + number + " was not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " was not found"));
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        BindingResult bindingResult = new BeanPropertyBindingResult(orderRequest,"orderRequest");
        validator.validate(orderRequest,bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation errors: " + bindingResult.getAllErrors());
        }

        Client client = clientRepository.findByDni(orderRequest.getDni()).orElseThrow(
                () -> new UserNotFoundException("Client with DNI " + orderRequest.getDni() + " was not found"));

        Order newOrder = OrderUtils.createOrder(orderRequest, client, orderRepository);

        List<Device> devices = deviceService.createDevice(orderRequest.getDevices(), newOrder);
        newOrder.setDevices(devices);

        Order savedOrder = orderRepository.save(newOrder);
        eventPublisher.publishEvent(new OrderCreateEvent(savedOrder, Action.CREO_ORDEN_TRABAJO));
        eventPublisher.publishEvent(new DeviceCreateEvent(savedOrder, Action.AGREGO_DISPOSITIVO));

        return savedOrder;
    }

    @Override
    @Transactional
    public Order updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " was not found"));

        Order originalOrder = OrderUtils.createOriginalOrder(existingOrder);

        existingOrder.setObservations(orderUpdateRequest.getObservations());

        List<Device> currentDevices = existingOrder.getDevices();
        List<Device> updatedDevices = deviceService.updateDevice(orderUpdateRequest.getDevices(), currentDevices);
        existingOrder.setDevices(updatedDevices);

        Order savedOrder = orderRepository.save(existingOrder);

        OrderUtils.generateMovement(originalOrder, orderUpdateRequest,eventPublisher);

        return savedOrder;
    }

    @Override
    @Transactional
    public void setActiveOrder(Long id, boolean active) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " was not found"));

        if (order.getActive() == active) {
            throw new IllegalStateException("The order is already in the desired state");
        }

        order.setActive(active);
        orderRepository.save(order);
        eventPublisher.publishEvent(new OrderUpdateEvent(order, active ? Action.ACTIVO_ORDEN_TRABAJO : Action.ELIMINO_ORDEN_TRABAJO));
    }
}
