package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.TechnicianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TechnicianServiceTest {
    @Mock
    private UserRepository<Technician> technicianRepository;
    @InjectMocks
    private TechnicianService technicianService;

    private UserRequestDTO sampleTechnicianRequest;

    @BeforeEach
    void setUp() {
        sampleTechnicianRequest = UserRequestDTO.builder()
                .name("Jhon Doe")
                .lastName("Doe")
                .email("asd5@asd.com")
                .phone("1122540454")
                .address("blanco 5126")
                .dni("94807938")
                .active(true)
                .password("12345678Nmmm")
                .build();
    }

}
