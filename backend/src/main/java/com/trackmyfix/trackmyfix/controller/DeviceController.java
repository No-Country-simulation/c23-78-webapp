package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.services.Impl.DeviceService;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/states")
    public ResponseEntity<List<String>> getAllStates() {
        List<String> states = deviceService.getAllStates();
        return ResponseEntity.ok(states);
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllTypes() {
        List<String> types = deviceService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Device>> findByState(@PathVariable State state) {
        List<Device> devices = deviceService.findByState(state);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Device>> findByState(@PathVariable Type type) {
        List<Device> devices = deviceService.findByType(type);
        return ResponseEntity.ok(devices);
    }
//    @PostMapping
//    public ResponseEntity<Device> createDevice(@RequestBody @Valid DeviceRequestDTO device) {
//        return deviceService.createDevice(device);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody @Valid DeviceRequestDTO device) {
//        return deviceService.updateDevice(id, device);
//    }
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
