package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserChangeRepository;
import com.trackmyfix.trackmyfix.services.Impl.UserChangeService;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.trackmyfix.trackmyfix.entity.ActionUser.AGREGO_CLIENTE;
import static com.trackmyfix.trackmyfix.entity.ActionUser.MODIFICO_DATOS_CLIENTE;
import static com.trackmyfix.trackmyfix.entity.Role.CLIENT;
import static com.trackmyfix.trackmyfix.entity.Role.TECHNICIAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserChangeServiceTest {
    @Mock
    private UserChangeRepository userChangeRepository;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private UserChangeService userChangeService;

    private static UserChange sampleUserChange;
    private static Technician sampleTechnician;
    private static Client sampleClient;
    private static UserChangeResponseDTO sampleUserChangeResponseDTO;

//    private Session session;
//    private Filter filter;

    @BeforeEach
    void init(){
//        session = Mockito.mock(Session.class);
//        filter = Mockito.mock(Filter.class);
//
//        when(entityManager.unwrap(Session.class)).thenReturn(session);
//        when(session.enableFilter("activeClientFilter")).thenReturn(filter);

        sampleTechnician = Technician.builder()
                .id(2L)
                .name("Jhony")
                .lastName("Bravo")
                .dni("41150350")
                .email("tech1@example.com")
                .address("example address 510")
                .role(TECHNICIAN)
                .password("1234")
                .build();
        sampleClient = Client.builder()
                .id(3L)
                .name("Pedro")
                .lastName("Picapiedra")
                .dni("41050350")
                .email("pedro@piedra.com")
                .address("example address 510")
                .role(CLIENT)
                .password("1234")
                .active(true)
                .build();

        sampleUserChange = new UserChange(1L,AGREGO_CLIENTE,sampleTechnician,sampleClient, new Date());

        sampleUserChangeResponseDTO = UserChangeResponseDTO.builder()
                .id(1L)
                .actionUser(AGREGO_CLIENTE.name())
                .client(mapToDTO(sampleClient))
                .technician(mapToDTO(sampleTechnician))
                .created_at(sampleUserChange.getCreatedAt())
                .build();
    }

    @AfterEach
    void then(){
        //session.disableFilter("activeClientFilter");
    }

    @Test
    @DisplayName("Test Find All user Changes")
    public void findAllTest(){

        UserChange userChange2 = new UserChange(2L, MODIFICO_DATOS_CLIENTE, sampleTechnician, sampleClient, new Date());
        given(userChangeRepository.findAll())
                .willReturn(List.of(sampleUserChange, userChange2));

        //filter.setParameter("isActive", false);
        Set<UserChangeResponseDTO> userChangeSet = userChangeService.findAll();

        assertThat(userChangeSet).isNotNull();
        assertThat(userChangeSet.size()).isEqualTo(2);
        assertThat(userChangeSet.iterator().next().getActionUser())
                .isEqualTo(sampleUserChangeResponseDTO.getActionUser());

        for(UserChangeResponseDTO userChange: userChangeSet) {
            if (userChange.getId() == 1L) {
                assertThat(userChange.getActionUser()).isEqualTo(AGREGO_CLIENTE.name());
            }
            if (userChange.getId() == 2L) {
                assertThat(userChange.getActionUser()).isEqualTo(MODIFICO_DATOS_CLIENTE.name());
            }
        }
    }

    @Test
    @DisplayName("Test Find All user Changes By Client Id")
    public void findByClient(){
        Client client = Client.builder()
                .id(4L)
                .name("Pablo")
                .lastName("Marmol")
                .dni("41050351")
                .email("pablo@example.com")
                .address("example address 510")
                .role(CLIENT)
                .password("1234")
                .active(false)
                .build();
        UserChange userChange2 = new UserChange(2L, MODIFICO_DATOS_CLIENTE, sampleTechnician, client, new Date());
        UserChange userChange3 = new UserChange(3L, AGREGO_CLIENTE, sampleTechnician, client, new Date());


        given(userChangeRepository.findByClientId(any(Long.class)))
                .willReturn(List.of(userChange2, userChange3));

        Set<UserChangeResponseDTO> clientChangesSet = userChangeService.findByClientId(4L);

        assertThat(clientChangesSet).isNotNull();
        assertThat(clientChangesSet.size()).isEqualTo(2);
    }

    private static UserResponseDTO mapToDTO(Client user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
    private static UserResponseDTO mapToDTO(Technician user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
}
