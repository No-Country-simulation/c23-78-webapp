package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.ClientService;
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
public class ClientServiceTest {

    @Mock
    private UserRepository<Client> clientRepository;
    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private ClientService clientService;


    private UserRequestDTO sampleClientRequest;

    @BeforeEach
    void setUp() {
        sampleClientRequest = UserRequestDTO.builder()
                .role(Role.CLIENT)
                .name("NombreClient1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd2@asd.com")
                .password("12345678Nmmm")
                .active(true)
                .build();
    }
    @DisplayName("Test for Saving Admin user")
    @Test
    public void testSaveUser(){
        //given
        Client client1 = Client.builder()
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


        when(clientRepository.save(any(Client.class)))
                .thenReturn(client1);
        clientService.save(clientRequest);
        verify(clientRepository,times(1)).save(any(Client.class));
    }

    @DisplayName("Test for finding user by id")
    @Test
    public void findById(){
        Client client = Client.builder()
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

        given(clientRepository.findById(any(Long.class)))
                .willReturn(Optional.of(client));

        UserResponseDTO userResponseDto = clientService.findById(2L);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo("client1");
    }
}
