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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
    private DeviceRequestDTO sampleDeviceRequest;
    private OrderRequest sampleOrderRequest;

    @BeforeEach
    void setup(){
        Client client1 = Client.builder()
                .role(Role.CLIENT)
                .name("Client1")
                .lastName("ClientLastName1")
                .dni("94808001")
                .address("calle client 1")
                .phone("1122540461")
                .email("client1@example.com")
                .active(true)
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
                new BigDecimal(0),
                client1,
                true,
                null,
                null,
                new ArrayList<>());

        sampleOrderRequest = new OrderRequest(
                client1.getDni(),
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

}
