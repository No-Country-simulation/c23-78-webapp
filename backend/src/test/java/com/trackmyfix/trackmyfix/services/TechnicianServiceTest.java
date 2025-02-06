package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.TechnicianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TechnicianServiceTest {
    @Mock
    private UserRepository<Technician> technicianRepository;
    @Mock
    private BCryptPasswordEncoder encoder;
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

    @DisplayName("Test for Saving Admin user")
    @Test
    public void testSaveUser(){
        //given
        Technician technician1 = Technician.builder()
                .id(1L)
                .name("client1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd3@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .password(encoder.encode("asdasdasd20"))
                .role(Role.CLIENT)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        UserRequestDTO clientRequest = UserRequestDTO.builder()
                .role(Role.CLIENT)
                .name("NombreAdmin1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("jindrg@gmail.com")
                .password("12345678Nmmm")
                .active(true)
                .build();


        when(technicianRepository.save(any(Technician.class)))
                .thenReturn(technician1);
        technicianService.save(clientRequest);
        verify(technicianRepository,times(1)).save(any(Technician.class));
    }

    @DisplayName("Test for finding user by id")
    @Test
    public void findById(){
        Technician technician = Technician.builder()
                .id(2L)
                .name("client1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd5@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .role(Role.ADMIN)
                .active(true)
                .password("asdasdasd20")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        given(technicianRepository.findById(any(Long.class)))
                .willReturn(Optional.of(technician));

        UserResponseDTO userResponseDto = technicianService.findById(2L);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo("client1");
    }

}
