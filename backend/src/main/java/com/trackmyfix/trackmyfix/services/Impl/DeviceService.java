package com.trackmyfix.trackmyfix.services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.exceptions.DeviceNotFoundException;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.services.IDeviceService;
import com.trackmyfix.trackmyfix.entity.State;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeviceService implements IDeviceService {
    private DeviceRepository deviceRepository;

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
                () -> new DeviceNotFoundException("El dispositivo con el numero" + id + "No fue encontrado"));
        return ResponseEntity.ok(device);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Device> findBySerialNumber(String serialNumber) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceNotFoundException(
                        "El dispositivo con el numero de serial" + serialNumber + "No fue encontrado"));
        return ResponseEntity.ok(device);
    }

    @Override
    public ResponseEntity<Device> createDevice(DeviceRequestDTO device) {
        Device newDevice = Device.builder()
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .accesories(device.getAccesories())
                .technicalReport(device.getTechnicalReport())
                .order(device.getOrder())
                .type(device.getType())
                .state(device.getState())
                .clientDescription(device.getClientDescription())
                .build();
        deviceRepository.save(newDevice);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Device> updateDevice(long id, DeviceRequestDTO device) {
        Device updatedDevice = deviceRepository.findById(id).orElseThrow(
                () -> new DeviceNotFoundException("El dispositivo con el numero" + id + "No fue encontrado"));
        updatedDevice.setAccesories(device.getAccesories());
        updatedDevice.setModel(device.getModel());
        updatedDevice.setOrder(device.getOrder());
        updatedDevice.setSerialNumber(device.getSerialNumber());
        updatedDevice.setState(device.getState());
        updatedDevice.setTechnicalReport(device.getTechnicalReport());
        updatedDevice.setType(device.getType());
        deviceRepository.save(updatedDevice);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    /*
     * metodo a confirmar con front end
     * 
     * @Override
     * public ResponseEntity<Device> changeStateDevice(long id, State newState) {
     * Device changedStateDevice = deviceRepository.findById(id).orElseThrow(
     * () -> new DeviceNotFoundException("El dispositivo con el numero" + id +
     * "No fue encontrado"));
     * changedStateDevice.setState(newState);
     * deviceRepository.save(changedStateDevice);
     * return new ResponseEntity<>(changedStateDevice, HttpStatus.OK);
     * }
     * 
     */
}
