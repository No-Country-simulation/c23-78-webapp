package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Admin;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository<Admin> adminRepository;
    @Autowired
    private UserRepository<Technician> technicianUserRepository;
    @Autowired
    private UserRepository<Client> clientUserRepository;

    private Admin admin;
    private Technician technician;
    private Client client;

    @BeforeEach
    void setUp() {
        admin = Admin.builder()
                .name("NombreAdmin1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd@asd.com")
                .role(Role.ADMIN)
                .password("12345678Nmmm")
                .build();
        technician = Technician.builder()
                .name("NombreTecnico1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd2@asd.com")
                .role(Role.TECHNICIAN)
                .password("12345678Nmmm")
                .build();
        client = Client.builder()
                .name("NombreCliente1")
                .lastName("Apellido")
                .dni("94807936")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd3@asd.com")
                .role(Role.CLIENT)
                .build();
    }

    @DisplayName("Test Admin Repository")
    @Test
    void testSaveAdmin() {
        Admin savedAdmin = adminRepository.save(admin);
        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getId()).isGreaterThan(0);
    }
}
