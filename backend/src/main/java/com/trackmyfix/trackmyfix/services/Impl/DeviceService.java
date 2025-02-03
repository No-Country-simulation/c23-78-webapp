package com.trackmyfix.trackmyfix.services.Impl;

import java.util.*;
import java.util.stream.Collectors;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.utils.DeviceUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.exceptions.DeviceNotFoundException;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.services.IDeviceService;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Service
@AllArgsConstructor
public class DeviceService implements IDeviceService {
    private DeviceRepository deviceRepository;
    private Validator validator;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        response.put("devices", devices);
        response.put("deviceSize", devices.size());
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Device> findById(long id) {
        Device device = deviceRepository.findById(id).orElseThrow(
                () -> new DeviceNotFoundException("The device with the number " + id + " was not found"));
        return ResponseEntity.ok(device);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Device> findBySerialNumber(String serialNumber) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceNotFoundException("The device with the serial number " + serialNumber + " was not found"));
        return ResponseEntity.ok(device);
    }

    @Override
    @Transactional
    public List<Device> findByState(State state) {
        List<Device> devices = deviceRepository.findByState(state);

        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found with the state " + state.name());
        }

        return devices;
    }

    @Override
    @Transactional
    public List<Device> findByType(Type type) {
        List<Device> devices = deviceRepository.findByType(type);

        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found with the type " + type.name());
        }

        return devices;
    }

    @Override
    @Transactional
    public List<Device> createDevice(List<DeviceRequestDTO> devices, Order newOrder) {
        DeviceUtils.validateDevices(devices, validator);

        List<Device> devicesCreate = new ArrayList<>();
        newOrder.getDevices().clear();
        for (DeviceRequestDTO deviceAux : devices) {
            Device device = DeviceUtils.transformDevice(deviceAux, newOrder);
            devicesCreate.add(device);
        }

        return devicesCreate;
    }

    @Override
    @Transactional
    public List<Device> updateDevice(List<DeviceRequestDTO> deviceRequestDTOs, List<Device> currentDevices) {
        if (deviceRequestDTOs.size() != currentDevices.size()) {
            throw new DeviceNotFoundException("The number of devices received does not match the number of devices in the order.");
        }

        List<Device> updatedDevices = new ArrayList<>();
        for (int i = 0; i < deviceRequestDTOs.size(); i++) {
            Device device = DeviceUtils.getDevice(deviceRequestDTOs, currentDevices, i);
            updatedDevices.add(device);
        }
        return updatedDevices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllStates() {
        return Arrays.stream(State.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllTypes() {
        return Arrays.stream(Type.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
