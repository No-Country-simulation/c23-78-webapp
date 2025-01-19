package com.trackmyfix.trackmyfix.configs;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository<Admin> adminRepository) {
        return args -> {
            // Crear usuarios tipo Admin
            Admin admin1 = Admin.builder()
                    .role(Role.ADMIN)
                    .name("NombreAdmin1")
                    .lastName("ApellidoAdmin1")
                    .dni("987654323")
                    .address("Calle Falsa 322")
                    .phone("0987654322")
                    .email("admin1@example.com")
                    .password("password1")
                    .active(true)
                    .build();

            Admin admin2 = Admin.builder()
                    .role(Role.ADMIN)
                    .name("NombreAdmin2")
                    .lastName("ApellidoAdmin2")
                    .dni("987654321")
                    .address("Calle Falsa 321")
                    .phone("0987654321")
                    .email("admin2@example.com")
                    .password("password2")
                    .active(true)
                    .build();

            adminRepository.saveAll(Arrays.asList(admin1, admin2));

            // Crear usuarios tipo Technician
//            Technician technician1 = new Technician(
//                    null,
//                    Role.TECHNICIAN,
//                    "NombreTechnician1",
//                    "ApellidoTechnician1",
//                    "223344556",
//                    "Calle Technico 123",
//                    "1122334455",
//                    "tech1@example.com",
//                    "password3",
//                    true,
//                    new Date(),
//                    new Date()
//            );
//
//            technicianRepository.save(technician1);
//
//            // Crear usuarios tipo Client
//            Client client1 = new Client(
//                    null,
//                    Role.CLIENT,
//                    "NombreClient1",
//                    "ApellidoClient1",
//                    "334455667",
//                    "Calle Cliente 123",
//                    "2211334455",
//                    "client1@example.com",
//                    true,
//                    new Date(),
//                    new Date()
//            );
//
//            clientRepository.save(client1);
        };
    }
}
