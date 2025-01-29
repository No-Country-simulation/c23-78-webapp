package com.trackmyfix.trackmyfix.utils;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DeviceUtils {

    public static Device getDevice(List<DeviceRequestDTO> deviceRequestDTOs, List<Device> currentDevices, int i) {
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
}
