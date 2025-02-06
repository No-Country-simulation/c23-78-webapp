package com.trackmyfix.trackmyfix.services;


import com.trackmyfix.trackmyfix.TrackmyfixApplication;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.event.DeviceCreateEvent;
import com.trackmyfix.trackmyfix.repository.MovementRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import com.trackmyfix.trackmyfix.services.Impl.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class MovementServiceTest {
    @Mock
    private MovementRepository movementRepository;
    @Mock
    private TechnicianRepository technicianRepository;
    @InjectMocks
    private MovementService movementService;

    private Movement sampleMovement;
    private Order sampleOrder;
    private Device sampleDevice;
    private Technician sampleTechnician;
    private Client sampleClient;

    @BeforeEach
    void setup(){

        sampleTechnician = Technician.builder()
                .id(1L)
                .name("client1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd3@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .password("asdasdasd20")
                .role(Role.CLIENT)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
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
        sampleMovement = Movement.builder()
                .idMovement(1L)
                .technician(sampleTechnician)
                .order(sampleOrder)
                .device(sampleDevice)
                .action(Action.AGREGO_DISPOSITIVO)
                .description("Description1")
                .movementDate(new Date())
                .build();
    }

    @Test
    @DisplayName("Test find all Movements")
    void testFindAllMovement(){
        given(movementRepository.findAll())
                .willReturn(List.of(sampleMovement));

        Map<String, Object> resultList = movementService.findAll();

        assertThat(resultList).isNotNull();
        assertThat(resultList.get("orderSize")).isEqualTo(1);
        List<Movement> movementList = (List<Movement>) resultList.get("movements");
        assertThat(movementList.get(0).getDescription()).isEqualTo(sampleMovement.getDescription());
    }

    @Test
    @DisplayName("Test Save Movement")
    void testSaveMovement() throws InstantiationException, IllegalAccessException {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication auth = Mockito.mock(Authentication.class);
        when(securityContext.getAuthentication())
                .thenReturn(auth);
        when(auth.getName())
                .thenReturn("tech1@example.com");
        SecurityContextHolder.setContext(securityContext);

        given(technicianRepository.findByEmail(any(String.class)))
                .willReturn(Optional.of(sampleTechnician));

        DeviceCreateEvent deviceCreateEvent = new DeviceCreateEvent(sampleOrder, Action.AGREGO_DISPOSITIVO);
        when(movementRepository.save(any(Movement.class)))
                .thenReturn(sampleMovement);
        movementService.handleOrderCreatedEvent(deviceCreateEvent);
        verify(movementRepository,times(1)).save(any(Movement.class));
    }

}
