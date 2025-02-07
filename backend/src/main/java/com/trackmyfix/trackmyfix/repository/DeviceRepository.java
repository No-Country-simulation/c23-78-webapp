package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Device;

import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    Optional<Device> findBySerialNumber(String serialNumber);
    List<Device> findByState(State state);
    List<Device> findByType(Type type);
}
