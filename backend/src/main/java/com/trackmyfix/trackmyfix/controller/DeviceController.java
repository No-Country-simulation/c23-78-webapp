package com.trackmyfix.trackmyfix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.services.Impl.DeviceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllDevices() {
        return deviceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> findById(@PathVariable Long id) {
        return deviceService.findById(id);
    }

    @GetMapping("/serial-number/{serialNumber}")
    public ResponseEntity<Device> findBySerialNumber(@PathVariable String serialNumber) {
        return deviceService.findBySerialNumber(serialNumber);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody @Valid DeviceRequestDTO device) {
        return deviceService.createDevice(device);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody @Valid DeviceRequestDTO device) {
        return deviceService.updateDevice(id, device);
    }
    /*
     * 
     * @PutMapping("/{id}/state")
     * public ResponseEntity<Device> changeStateDevice(@PathVariable Long
     * id, @RequestBody State newState) {
     * return deviceService.changeStateDevice(id, newState);
     * }
     * 
     */
}
