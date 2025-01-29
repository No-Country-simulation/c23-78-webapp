package com.trackmyfix.trackmyfix.services;

import java.util.List;
import java.util.Map;

import com.trackmyfix.trackmyfix.entity.Order;
import org.springframework.http.ResponseEntity;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;

public interface IDeviceService {
    ResponseEntity<Map<String, Object>> findAll();

    ResponseEntity<Device> findById(long id);

    ResponseEntity<Device> findBySerialNumber(String serialNumber);

    List<Device> createDevice(List<DeviceRequestDTO> devices, Order newOrder);

    List<Device> updateDevice(List<DeviceRequestDTO> devicesRequest, List<Device> currentDevices);

    List<String>getAllStates();
    List<String>getAllTypes();

    /*
     * 
     * ResponseEntity<Device> changeStateDevice(long id, State newState);
     */
}
