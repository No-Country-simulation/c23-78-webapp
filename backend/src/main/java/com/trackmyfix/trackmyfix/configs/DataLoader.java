package com.trackmyfix.trackmyfix.configs;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.AdminRepository;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(AdminRepository adminRepository, TechnicianRepository technicianRepository, ClientRepository clientRepository) {
        return args -> {
            // Crear usuario tipo Admin
            Admin admin1 = Admin.builder()
                    .role(Role.ADMIN)
                    .name("NombreAdmin1")
                    .lastName("Apellido")
                    .dni("94807936")
                    .address("blanco 5126")
                    .phone("1122540454")
                    .email("x_loquito_x@gmail.com")
                    .password("12345678Nmmm")
                    .active(true)
                    .build();
            adminRepository.save(admin1);

            // Crear usuarios tipo Technician
            Technician technician1 = Technician.builder()
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
            Technician technician2 = Technician.builder()
                    .role(Role.TECHNICIAN)
                    .name("Technician2")
                    .lastName("TechLastName2")
                    .dni("94807938")
                    .address("calle 124")
                    .phone("1122540456")
                    .email("tech2@example.com")
                    .password("techpassword2")
                    .active(true)
                    .build();
            Technician technician3 = Technician.builder()
                    .role(Role.TECHNICIAN)
                    .name("Technician3")
                    .lastName("TechLastName3")
                    .dni("94807939")
                    .address("calle 125")
                    .phone("1122540457")
                    .email("tech3@example.com")
                    .password("techpassword3")
                    .active(true)
                    .build();
            technicianRepository.saveAll(List.of(technician1, technician2, technician3));

            // Crear usuarios tipo Client
            Client client1 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client1")
                    .lastName("ClientLastName1")
                    .dni("94808001")
                    .address("calle client 1")
                    .phone("1122540461")
                    .email("client1@example.com")
                    .active(true)
                    .build();


            Client client2 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client2")
                    .lastName("ClientLastName2")
                    .dni("94808002")
                    .address("calle client 2")
                    .phone("1122540462")
                    .email("client2@example.com")
                    .active(true)
                    .build();

            Client client3 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client3")
                    .lastName("ClientLastName3")
                    .dni("94808003")
                    .address("calle client 3")
                    .phone("1122540463")
                    .email("client3@example.com")
                    .active(true)
                    .build();

            Client client4 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client4")
                    .lastName("ClientLastName4")
                    .dni("94808004")
                    .address("calle client 4")
                    .phone("1122540464")
                    .email("client4@example.com")
                    .active(true)
                    .build();

            Client client5 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client5")
                    .lastName("ClientLastName5")
                    .dni("94808005")
                    .address("calle client 5")
                    .phone("1122540465")
                    .email("client5@example.com")
                    .active(true)
                    .build();

            Client client6 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client6")
                    .lastName("ClientLastName6")
                    .dni("94808006")
                    .address("calle client 6")
                    .phone("1122540466")
                    .email("client6@example.com")
                    .active(true)
                    .build();

            Client client7 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client7")
                    .lastName("ClientLastName7")
                    .dni("94808007")
                    .address("calle client 7")
                    .phone("1122540467")
                    .email("client7@example.com")
                    .active(true)
                    .build();

            Client client8 = Client.builder()
                    .role(Role.CLIENT)
                    .name("Client8")
                    .lastName("ClientLastName8")
                    .dni("94808008")
                    .address("calle client 8")
                    .phone("1122540468")
                    .email("client8@example.com")
                    .active(true)
                    .build();
            clientRepository.saveAll(List.of(client1, client2, client3,client4,client5,client6,client7,client8));
        };
    }
}
