package com.trackmyfix.trackmyfix.services;

import java.util.List;
import java.util.Map;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;
import org.springframework.http.ResponseEntity;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;

public interface IDeviceService {
    Map<String, Object> findAll();
    Device findById(long id);
    Device findBySerialNumber(String serialNumber);
    List<Device> findByState(State state);
    List<Device> findByType(Type type);
    List<Device> createDevice(List<DeviceRequestDTO> devices, Order newOrder);
    List<Device> updateDevice(List<DeviceRequestDTO> devicesRequest, List<Device> currentDevices);
    List<String>getAllStates();
    List<String>getAllTypes();

}
