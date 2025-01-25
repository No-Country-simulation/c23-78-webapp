package com.trackmyfix.trackmyfix.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.State;

public interface IDeviceService {
    ResponseEntity<Map<String, Object>> findAll();

    ResponseEntity<Device> findById(long id);

    ResponseEntity<Device> findBySerialNumber(String serialNumber);

    ResponseEntity<Device> createDevice(DeviceRequestDTO device);

    ResponseEntity<Device> updateDevice(long id, DeviceRequestDTO device);
    /*
     * 
     * ResponseEntity<Device> changeStateDevice(long id, State newState);
     */
}
