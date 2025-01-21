package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Admin;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserRepository<Admin> adminRepository;

    @InjectMocks
    private AdminService adminService;

    private UserRequestDTO sampleAdminRequest;

    @BeforeEach
    void setUp() {
        sampleAdminRequest = UserRequestDTO.builder()
                .role(Role.ADMIN)
                .name("NombreAdmin1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd1@asd.com")
                .password("12345678Nmmm")
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
                .password("asdasdasd20")
                .role(Role.ADMIN)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        UserRequestDTO adminRequest = UserRequestDTO.builder()
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


        when(adminRepository.save(any(Admin.class)))
                .thenReturn(admin1);
        adminService.save(adminRequest);
        verify(adminRepository,times(1)).save(any(Admin.class));
    }

    @DisplayName("Test for finding user by id")
    @Test
    public void findById(){
        Admin admin = Admin.builder()
                .id(2L)
                .name("Admin1")
                .lastName("Apellido 1")
                .dni("45651256")
                .email("asd@asd.com")
                .address("evergreen avenue 150")
                .phone("1154658599")
                .role(Role.ADMIN)
                .active(true)
                .password("asdasdasd20")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        given(adminRepository.findById(any(Long.class)))
                .willReturn(Optional.of(admin));

        UserResponseDTO userResponseDto = adminService.findById(2L);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo("Admin1");
    }
}
