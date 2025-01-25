package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Device;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    Optional<Device> findBySerialNumber(String serialNumber);
}
