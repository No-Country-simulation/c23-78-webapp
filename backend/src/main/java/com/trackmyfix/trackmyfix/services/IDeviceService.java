package com.trackmyfix.trackmyfix.services;

import java.util.List;
import java.util.Map;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.event.DeviceEvent;
import org.springframework.http.ResponseEntity;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.State;

public interface IDeviceService {
    ResponseEntity<Map<String, Object>> findAll();

    ResponseEntity<Device> findById(long id);

    ResponseEntity<Device> findBySerialNumber(String serialNumber);

    void createDevice(DeviceEvent deviceEvent);

    ResponseEntity<Device> updateDevice(long id, DeviceRequestDTO device);


    /*
     * 
     * ResponseEntity<Device> changeStateDevice(long id, State newState);
     */
}
