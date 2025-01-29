package com.trackmyfix.trackmyfix.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.event.DeviceCreateEvent;
import com.trackmyfix.trackmyfix.event.OrderCreateEvent;
import com.trackmyfix.trackmyfix.event.OrderUpdateEvent;
import com.trackmyfix.trackmyfix.exceptions.InvalidPriceException;
import com.trackmyfix.trackmyfix.exceptions.OrderNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.*;
import com.trackmyfix.trackmyfix.services.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final DeviceService deviceService;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Order> orders = (List<Order>) orderRepository.findAll();
        response.put("orders", orders);
        response.put("orderSize", orders.size());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findByNumber(String number) {
        return orderRepository.findByNumber(number)
                .orElseThrow(() -> new OrderNotFoundException("Orden con número " + number + " no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        Client client = clientRepository.findByDni(orderRequest.getDni()).orElseThrow(
                () -> new UserNotFoundException("Cliente con DNI " + orderRequest.getDni() + " no encontrado"));

        Order newOrder = Order.builder()
                .number(generateOrderNumber())
                .observations(orderRequest.getObservations())
                .client(client)
                .active(true)
                .orderTotal(BigDecimal.ZERO)
                .devices(new ArrayList<>())
                .build();

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
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + orderId + " no encontrada"));

        // Guardar una copia profunda de los datos antiguos
        Order originalOrder = Order.builder()
                .idOrder(existingOrder.getIdOrder())
                .observations(existingOrder.getObservations())
                .devices(new ArrayList<>(existingOrder.getDevices().stream()
                        .map(device -> Device.builder()
                                .idDevice(device.getIdDevice())
                                .model(device.getModel())
                                .serialNumber(device.getSerialNumber())
                                .accessories(device.getAccessories())
                                .initialPrice(device.getInitialPrice())
                                .finalPrice(device.getFinalPrice())
                                .clientDescription(device.getClientDescription())
                                .technicalReport(device.getTechnicalReport())
                                .type(device.getType())
                                .state(device.getState())
                                .order(device.getOrder())
                                .build())
                        .collect(Collectors.toList())))
                .build();


        existingOrder.setObservations(orderUpdateRequest.getObservations());

        List<Device> currentDevices = existingOrder.getDevices();
        List<Device> updatedDevices = deviceService.updateDevice(orderUpdateRequest.getDevices(), currentDevices);
        existingOrder.setDevices(updatedDevices);

        Order savedOrder = orderRepository.save(existingOrder);

        Map<String, Object> changes = this.detectChanges(originalOrder, orderUpdateRequest);





        return savedOrder;
    }



    @Override
    @Transactional
    public void deactivateOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));

        if (!order.getActive()) {
            throw new IllegalStateException("La orden ya está inactiva");
        }

        order.setActive(false);
        orderRepository.save(order);

        eventPublisher.publishEvent(new OrderUpdateEvent(order, Action.ELIMINO_ORDEN_TRABAJO));
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

    private Map<String, Object> detectChanges(Order originalOrder, OrderUpdateRequest orderUpdateRequest) {
        Map<String, Object> changes = new HashMap<>();

        // Comparar observaciones
        if (!originalOrder.getObservations().equals(orderUpdateRequest.getObservations())) {
            changes.put("Observaciones", "De: [" + originalOrder.getObservations() + "] a: [" + orderUpdateRequest.getObservations() + "]");
            eventPublisher.publishEvent(new OrderUpdateEvent(Order, Action.MODIFICO_ORDEN_TRABAJO, changes));
        }

        // Comparar dispositivos
        List<Device> originalDevices = originalOrder.getDevices();
        List<DeviceRequestDTO> updatedDevices = orderUpdateRequest.getDevices();

        for (int i = 0; i < originalDevices.size(); i++) {
            Device originalDevice = originalDevices.get(i);
            DeviceRequestDTO updatedDevice = updatedDevices.get(i);

            if (!originalDevice.getSerialNumber().equals(updatedDevice.getSerialNumber())) {
                changes.put("SerialNumber", "De: [" + originalDevice.getSerialNumber() + "] a: [" + updatedDevice.getSerialNumber() + "]");
            }
            if (!originalDevice.getModel().equals(updatedDevice.getModel())) {
                changes.put("Model", "De: [" + originalDevice.getModel() + "] a: [" + updatedDevice.getModel() + "]");
            }
            if (!originalDevice.getAccessories().equals(updatedDevice.getAccessories())) {
                changes.put("Accessories", "De: [" + originalDevice.getAccessories() + "] a: [" + updatedDevice.getAccessories() + "]");
            }
            if (!originalDevice.getInitialPrice().equals(updatedDevice.getInitialPrice())) {
                changes.put("InitialPrice", "De: [" + originalDevice.getInitialPrice() + "] a: [" + updatedDevice.getInitialPrice() + "]");
            }
            if (originalDevice.getFinalPrice() != null && !originalDevice.getFinalPrice().equals(updatedDevice.getFinalPrice())) {
                changes.put("FinalPrice", "De: [" + originalDevice.getFinalPrice() + "] a: [" + updatedDevice.getFinalPrice() + "]");
            } else if (originalDevice.getFinalPrice() == null && updatedDevice.getFinalPrice() != null) {
                changes.put("FinalPrice", "De: [null] a: [" + updatedDevice.getFinalPrice() + "]");
            }
            if (!originalDevice.getClientDescription().equals(updatedDevice.getClientDescription())) {
                changes.put("ClientDescription", "De: [" + originalDevice.getClientDescription() + "] a: [" + updatedDevice.getClientDescription() + "]");
            }
            if (!originalDevice.getTechnicalReport().equals(updatedDevice.getTechnicalReport())) {
                changes.put("TechnicalReport", "De: [" + originalDevice.getTechnicalReport() + "] a: [" + updatedDevice.getTechnicalReport() + "]");
            }
            if (!originalDevice.getType().equals(updatedDevice.getType())) {
                changes.put("Type", "De: [" + originalDevice.getType() + "] a: [" + updatedDevice.getType() + "]");
            }
            if (!originalDevice.getState().equals(updatedDevice.getState())) {
                changes.put("State", "De: [" + originalDevice.getState() + "] a: [" + updatedDevice.getState() + "]");
            }
        }

        return changes;
    }


}
