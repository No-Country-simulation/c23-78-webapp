package com.trackmyfix.trackmyfix.configs;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(AdminRepository adminRepository) {
        return args -> {
            // Crear usuarios tipo Admin
            Admin admin1 = new Admin(
                    null,
                    Role.ADMIN,
                    "NombreAdmin1",
                    "ApellidoAdmin1",
                    "123456789",
                    "Calle Falsa 123",
                    "1234567890",
                    "admin1@example.com",
                    "password1",
                    true,
                    new Date(),
                    new Date()
            );

            Admin admin2 = new Admin(
                    null,
                    Role.ADMIN,
                    "NombreAdmin2",
                    "ApellidoAdmin2",
                    "987654321",
                    "Avenida Siempre Viva 456",
                    "0987654321",
                    "admin2@example.com",
                    "password2",
                    true,
                    new Date(),
                    new Date()
            );

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
