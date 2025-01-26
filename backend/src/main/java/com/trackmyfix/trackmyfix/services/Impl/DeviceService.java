package com.trackmyfix.trackmyfix.services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.event.DeviceEvent;
import org.springframework.context.event.EventListener;
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
    @EventListener
    public void createDevice(DeviceEvent event) {

        for (DeviceRequestDTO deviceRequest : event.getDevices()) {
            Device device = new Device();
            device.setModel(deviceRequest.getModel());
            device.setSerialNumber(deviceRequest.getSerialNumber());
            device.setAccessories(deviceRequest.getAccessories());
            device.setInitialPrice(deviceRequest.getInitialPrice());
            device.setFinalPrice(deviceRequest.getFinalPrice());
            device.setClientDescription(deviceRequest.getClientDescription());
            device.setTechnicalReport(deviceRequest.getTechnicalReport());
            device.setType(deviceRequest.getType());
            device.setState(State.RECIBIDO);
            device.setOrder(event.getOrder());

            deviceRepository.save(device); // Guardar el dispositivo en la base de datos
        }
    }

    @Override
    public ResponseEntity<Device> updateDevice(long id, DeviceRequestDTO device) {
        Device updatedDevice = deviceRepository.findById(id).orElseThrow(
                () -> new DeviceNotFoundException("El dispositivo con el numero" + id + "No fue encontrado"));
        updatedDevice.setAccessories(device.getAccessories());
        updatedDevice.setModel(device.getModel());
        //updatedDevice.setOrder(device.getOrder());
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
