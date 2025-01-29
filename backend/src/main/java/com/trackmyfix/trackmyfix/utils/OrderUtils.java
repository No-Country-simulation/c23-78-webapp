package com.trackmyfix.trackmyfix.utils;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.Dto.Request.OrderUpdateRequest;
import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.event.DeviceStateChangeEvent;
import com.trackmyfix.trackmyfix.event.OrderUpdateEvent;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationEventPublisher;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class OrderUtils {

    public static String generateOrderNumber(OrderRepository orderRepository) {
        String lastOrderNumber = getLastOrderNumber(orderRepository);
        int lastNumber = lastOrderNumber != null ? extractNumberFromOrder(lastOrderNumber) : 0;
        int newOrderNumber = lastNumber + 1;
        return formatOrderNumber(newOrderNumber);
    }

    public static Order createOrder(OrderRequest orderRequest, Client client, OrderRepository orderRepository) {
        return Order.builder()
                .number(generateOrderNumber(orderRepository)) // Llamada a generar el número de orden
                .observations(orderRequest.getObservations())
                .client(client)
                .active(true)
                .orderTotal(BigDecimal.ZERO)
                .devices(new ArrayList<>())
                .build();
    }

    private static String getLastOrderNumber(OrderRepository orderRepository) {
        return orderRepository.findTopByOrderByIdOrderDesc()
                .map(Order::getNumber)
                .orElse(null);
    }

    private static int extractNumberFromOrder(String orderNumber) {
        return Integer.parseInt(orderNumber.replace("ORD-", ""));
    }

    private static String formatOrderNumber(int number) {
        return "ORD-" + String.format("%05d", number);
    }

    //Update
    public static Order createOriginalOrder(Order existingOrder) {
        return Order.builder()
                .idOrder(existingOrder.getIdOrder())
                .observations(existingOrder.getObservations())
                .devices(existingOrder.getDevices().stream()
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
                        .collect(Collectors.toList()))
                .build();
    }


    public static void generateMovement(Order originalOrder, OrderUpdateRequest orderUpdateRequest, ApplicationEventPublisher eventPublisher) {
        Map<String, Object> changes = new HashMap<>();
        StringBuilder descriptionChanges = new StringBuilder(originalOrder.getObservations());

        if (!originalOrder.getObservations().equals(orderUpdateRequest.getObservations())) {
            addChange("Observaciones", originalOrder.getObservations(), orderUpdateRequest.getObservations(), changes, descriptionChanges);
            eventPublisher.publishEvent(new OrderUpdateEvent(originalOrder, Action.MODIFICO_ORDEN_TRABAJO, changes));
        }

        List<Device> originalDevices = originalOrder.getDevices();
        List<DeviceRequestDTO> updatedDevices = orderUpdateRequest.getDevices();

        for (int i = 0; i < originalDevices.size(); i++) {
            Device originalDevice = originalDevices.get(i);
            DeviceRequestDTO updatedDevice = updatedDevices.get(i);

            verifyChangeDevice(originalDevice, updatedDevice, changes, descriptionChanges, eventPublisher);
        }
    }

    private static void addChange(String campo, String valorOriginal, String valorNuevo, Map<String, Object> changes, StringBuilder descriptionChanges) {
        String change = String.format("%s cambió de [%s] a [%s]", campo, valorOriginal, valorNuevo);
        changes.put(campo, change);
        descriptionChanges.append("\n").append(change);
    }

    private static void verifyChangeDevice(Device originalDevice, DeviceRequestDTO updatedDevice, Map<String, Object> changes, StringBuilder descriptionChanges, ApplicationEventPublisher eventPublisher) {
        if (!originalDevice.getSerialNumber().equals(updatedDevice.getSerialNumber())) {
            addChange("SerialNumber", originalDevice.getSerialNumber(), updatedDevice.getSerialNumber(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_NROSERIE_DISPOSITIVO, (String) changes.get("SerialNumber")));
        }
        if (!originalDevice.getModel().equals(updatedDevice.getModel())) {
            addChange("Model", originalDevice.getModel(), updatedDevice.getModel(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_MODELO_DISPOSITIVO, (String) changes.get("Model")));
        }
        if (!originalDevice.getAccessories().equals(updatedDevice.getAccessories())) {
            addChange("Accessories", originalDevice.getAccessories(), updatedDevice.getAccessories(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_ACCESORIOS_DISPOSITIVO, (String) changes.get("Accessories")));
        }
        if (!originalDevice.getInitialPrice().equals(updatedDevice.getInitialPrice())) {
            addChange("InitialPrice", originalDevice.getInitialPrice().toString(), updatedDevice.getInitialPrice().toString(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_PRECIO_INICIAL_DISPOSITIVO, (String) changes.get("InitialPrice")));
        }
        if (!originalDevice.getFinalPrice().equals(updatedDevice.getFinalPrice())) {
            addChange("FinalPrice", originalDevice.getFinalPrice().toString(), updatedDevice.getFinalPrice() != null ? updatedDevice.getFinalPrice().toString() : "null", changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_PRECIO_FINAL_DISPOSITIVO, (String) changes.get("FinalPrice")));
        }
        if (!originalDevice.getClientDescription().equals(updatedDevice.getClientDescription())) {
            addChange("ClientDescription", originalDevice.getClientDescription(), updatedDevice.getClientDescription(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_REPORTE_CLIENTE_DISPOSITIVO, (String) changes.get("ClientDescription")));
        }
        if (!originalDevice.getTechnicalReport().equals(updatedDevice.getTechnicalReport())) {
            addChange("TechnicalReport", originalDevice.getTechnicalReport(), updatedDevice.getTechnicalReport(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_REPORTE_TECNICO_DISPOSITIVO, (String) changes.get("TechnicalReport")));
        }
        if (!originalDevice.getType().equals(updatedDevice.getType())) {
            addChange("Type", originalDevice.getType().name(), updatedDevice.getType().name(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_TIPO_DISPOSITIVO, (String) changes.get("Type")));
        }
        if (!originalDevice.getState().equals(updatedDevice.getState())) {
            addChange("State", originalDevice.getState().name(), updatedDevice.getState().name(), changes, descriptionChanges);
            eventPublisher.publishEvent(new DeviceStateChangeEvent(originalDevice, Action.CAMBIO_ESTADO_DISPOSITIVO, (String) changes.get("State")));
        }
    }


}