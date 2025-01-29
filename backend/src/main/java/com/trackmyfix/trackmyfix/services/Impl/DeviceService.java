package com.trackmyfix.trackmyfix.services.Impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.trackmyfix.trackmyfix.entity.Order;
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
    @Transactional
    public List<Device> createDevice(List<DeviceRequestDTO> devices, Order newOrder) {

        List<Device> devicesCreate = new ArrayList<>();
        newOrder.getDevices().clear();
        for (DeviceRequestDTO deviceAux : devices) {
            Device device = new Device();
            device.setModel(deviceAux.getModel());
            device.setSerialNumber(deviceAux.getSerialNumber());
            device.setAccessories(deviceAux.getAccessories());
            device.setInitialPrice(deviceAux.getInitialPrice());
            device.setFinalPrice(deviceAux.getFinalPrice());
            device.setClientDescription(deviceAux.getClientDescription());
            device.setTechnicalReport(deviceAux.getTechnicalReport());
            device.setType(deviceAux.getType());
            device.setState(State.RECIBIDO);
            device.setOrder(newOrder);

            devicesCreate.add(device);
        }
        return devicesCreate;
    }

    public List<Device> updateDevice(List<DeviceRequestDTO> deviceRequestDTOs, List<Device> currentDevices) {
        List<Device> updatedDevices = new ArrayList<>();

        if (deviceRequestDTOs.size() != currentDevices.size()) {
            throw new DeviceNotFoundException("El número de dispositivos recibidos no coincide con el número de dispositivos en la orden.");
        }

        for (int i = 0; i < deviceRequestDTOs.size(); i++) {
            Device device = getDevice(deviceRequestDTOs, currentDevices, i);
            updatedDevices.add(device);
        }
        return updatedDevices;
    }

    private static Device getDevice(List<DeviceRequestDTO> deviceRequestDTOs, List<Device> currentDevices, int i) {
        DeviceRequestDTO deviceDTO = deviceRequestDTOs.get(i);
        Device device = currentDevices.get(i);

        device.setModel(deviceDTO.getModel());
        device.setSerialNumber(deviceDTO.getSerialNumber());
        device.setAccessories(deviceDTO.getAccessories());
        device.setInitialPrice(deviceDTO.getInitialPrice());
        device.setFinalPrice(deviceDTO.getFinalPrice());
        device.setClientDescription(deviceDTO.getClientDescription());
        device.setTechnicalReport(deviceDTO.getTechnicalReport());
        device.setType(deviceDTO.getType());
        device.setState(deviceDTO.getState());
        device.setOrder(device.getOrder());
        return device;
    }


    @Override
    public List<String> getAllStates() {
        return Arrays.stream(State.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllTypes() {
        return Arrays.stream(Type.values())
                .map(Enum::name)
                .collect(Collectors.toList());
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
