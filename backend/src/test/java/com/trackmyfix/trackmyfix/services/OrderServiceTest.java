package com.trackmyfix.trackmyfix.services;


import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import com.trackmyfix.trackmyfix.services.Impl.DeviceService;
import com.trackmyfix.trackmyfix.services.Impl.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @Mock
    private DeviceService deviceService;
    @Mock
    private Validator validator;
    @InjectMocks
    private OrderService orderService;

    private Order sampleOrder;
    private Device sampleDevice;
    private DeviceRequestDTO sampleDeviceRequest;
    private OrderRequest sampleOrderRequest;
    private Client sampleClient;

    @BeforeEach
    void setup(){
        sampleClient = Client.builder()
                .role(Role.CLIENT)
                .name("Client1")
                .lastName("ClientLastName1")
                .dni("94808001")
                .address("calle client 1")
                .phone("1122540461")
                .email("client1@example.com")
                .active(true)
                .build();
        sampleDevice = Device.builder()
                .idDevice(1L)
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
        sampleDeviceRequest = new DeviceRequestDTO(
                null,
                "MacBook Pro 2020",
                "SN-00002",
                "Cargador original",
                new BigDecimal(2000),
                new BigDecimal(20000),
                "Pantalla con rayas",
                "Falla en GPU",
                Type.NOTEBOOK,
                State.RECIBIDO,
                null
        );
        sampleOrder = new Order(
                1L,
                "ORD-00001",
                "Observation1",
                sampleDevice.getFinalPrice(),
                sampleClient,
                true,
                new Date(),
                null,
                List.of(sampleDevice));

        sampleOrderRequest = new OrderRequest(
                sampleClient.getDni(),
                "Obervation1",
                List.of(sampleDeviceRequest)
        );
    }

    @Test
    @DisplayName("Test findAll orders")
    void testFindAll(){
        Client client2 = Client.builder()
                .role(Role.CLIENT)
                .name("Client2")
                .lastName("ClientLastName2")
                .dni("94808002")
                .address("calle client 2")
                .phone("1122540462")
                .email("client2@example.com")
                .active(true)
                .build();
        Order order2 = new Order(
                2L,
                "ORD-00002",
                "Observation2",
                new BigDecimal(0),
                client2,
                true,
                null,
                null,
                new ArrayList<>());
        given(orderRepository.findAll())
                .willReturn(List.of(sampleOrder,order2));

        Map<String, Object> resultList = orderService.findAll();

        assertThat(resultList).isNotNull();
        assertThat(resultList.get("orderSize")).isEqualTo(resultList.size());
        List<Order> orderList = (List<Order>) resultList.get("orders");
        assertThat(orderList.get(0).getObservations()).isEqualTo(sampleOrder.getObservations());
    }
    @Test
    @DisplayName("Test save orders")
    void testSave(){
        given(clientRepository.findByDni(any(String.class)))
                .willReturn(Optional.of(sampleClient));

        when(orderRepository.save(any(Order.class)))
                .thenReturn(sampleOrder);
        Order created = orderService.createOrder(sampleOrderRequest);
        verify(orderRepository,times(1)).save(any(Order.class));
    }
}
