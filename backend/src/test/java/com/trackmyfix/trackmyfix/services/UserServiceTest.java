package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @Mock
    private UserRepository<User> userRepository;
    @Mock
    private UserRepository<Admin> adminRepository;
    @Mock
    private UserRepository<Technician> technicianRepository;
    @Mock
    private UserRepository<Client> clientRepository;

    @InjectMocks
    private UserService userService;

    private UserRequestDTO sampleAdminRequest;
    private UserRequestDTO sampleTechnicianRequest;
    private UserRequestDTO sampleClientRequest;

    @BeforeEach
    void setUp(){
        sampleAdminRequest = UserRequestDTO.builder()
            .role(Role.ADMIN)
            .name("NombreAdmin1")
            .lastName("Apellido")
            .dni("94807936")
            .address("blanco 5126")
            .phone("1122540454")
            .email("jindrg@gmail.com")
            .password("12345678Nmmm")
            .active(true)
            .build();

        sampleTechnicianRequest = UserRequestDTO.builder()
            .role(Role.TECHNICIAN)
            .name("Technician1")
            .lastName("TechLastName1")
            .dni("94807937")
            .address("calle 123")
            .phone("1122540455")
            .email("tech1@example.com")
            .password("techpassword1")
            .active(true)
            .build();
        sampleClientRequest = UserRequestDTO.builder()
                .role(Role.CLIENT)
                .name("Client1")
                .lastName("ClientLastName1")
                .dni("94808001")
                .address("calle client 1")
                .phone("1122540461")
                .email("client1@example.com")
                .active(true)
                .build();
    }
    @DisplayName("Test for Saving Admin user")
    @Test
    public void testSaveUser(){
        //given
        Admin admin1 = Admin.builder()
                .id(1L)
                .name("Admin1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .role(Role.ADMIN)
                .build();

        when(adminRepository.save(any(Admin.class)))
                .thenReturn(admin1);
        sampleAdminRequest.setId(admin1.getId());
        userService.save(sampleAdminRequest);
        verify(adminRepository,times(1)).save(any(Admin.class));
    }

    @DisplayName("Test for Saving Admin user")
    @Test
    public void findById(){
        Technician technician = Technician.builder()
                .id(1L)
                .name("Technician1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .role(Role.TECHNICIAN)
                .active(true)
                .password("asdasdasd20")
                .build();

        given(technicianRepository.findById(any(Long.class)))
                .willReturn(Optional.of(technician));

        UserResponseDTO userResponseDto = userService.findById(1L);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo("Technician1");
    }
}
