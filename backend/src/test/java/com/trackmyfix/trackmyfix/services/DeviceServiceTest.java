package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.services.Impl.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private DeviceService deviceService;

    private Device sampleDevice;

    @BeforeEach
    void setup(){
        sampleDevice = Device.builder()
                .idDevice(1L)
                .model("Dell OptiPlex 3080")
                .serialNumber("SN-00001")
                .accessories("Teclado y Mouse")
                .initialPrice(new BigDecimal(3000))
                .finalPrice(new BigDecimal(30000))
                .clientDescription("No enciende")
                .technicalReport("Fuente dañada")
                .type(Type.COMPUTADORA_DE_ESCRITORIO)
                .state(State.RECIBIDO)
                .createdAt(new Date())
                .build();
    }

    @Test
    @DisplayName("Test Find Device by Id")
    void testFindById(){
        given(deviceRepository.findById(any(Long.class)))
                .willReturn(Optional.of(sampleDevice));

        Device foundDevice = deviceService.findById(1L);

        assertThat(foundDevice).isNotNull();
        assertThat(foundDevice.getIdDevice()).isEqualTo(sampleDevice.getIdDevice());
        assertThat(foundDevice.getModel()).isEqualTo(sampleDevice.getModel());
    }

    @Test
    @DisplayName("Test Find All")
    void testFindAllDevice(){
        Device device2 = Device.builder()
                .idDevice(2L)
                .model("MacBook Pro 2020")
                .serialNumber("SN-00002")
                .accessories("Cargador original")
                .initialPrice(new BigDecimal(2000))
                .finalPrice(new BigDecimal(20000))
                .clientDescription("Pantalla con rayas")
                .technicalReport("Falla en GPU")
                .type(Type.NOTEBOOK)
                .state(State.RECIBIDO)
                .createdAt(new Date())
                .build();
        List<Device> deviceList = List.of(sampleDevice, device2);
        given(deviceRepository.findAll())
                .willReturn(deviceList);

        Map<String, Object> servDevicesList = deviceService.findAll();

        assertThat(servDevicesList).isNotNull();
        assertThat(servDevicesList.get("deviceSize")).isEqualTo(deviceList.size());
        List<Device> result = (List<Device>) servDevicesList.get("devices");
        assertThat(result.get(0).getModel()).isEqualTo(sampleDevice.getModel());
    }

}
