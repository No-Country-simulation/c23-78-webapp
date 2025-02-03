package com.trackmyfix.trackmyfix.utils;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import jakarta.validation.ValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

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

    public static void validateDevices(List<DeviceRequestDTO> devices, Validator validator) {
        for (DeviceRequestDTO device : devices) {
            BindingResult bindingResult = new BeanPropertyBindingResult(device, "deviceRequestDTO");
            validator.validate(device, bindingResult);

            if (bindingResult.hasErrors()) {
                throw new ValidationException("Validation errors: " + bindingResult.getAllErrors());
            }
        }
    }

    public static Device transformDevice(DeviceRequestDTO deviceAux, Order newOrder) {
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
        return device;
    }
}
