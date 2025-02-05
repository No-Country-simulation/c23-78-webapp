package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import com.trackmyfix.trackmyfix.repository.UserChangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class UserChangeDataLoader {
    private UserChangeRepository userChangeRepository;
    private TechnicianRepository technicianRepository;
    private ClientRepository clientRepository;

    public void load() {
        List<Technician> technicians = (List<Technician>) this.technicianRepository.findAll();
        List<Client> clients = (List<Client>) this.clientRepository.findAll();
        UserChange userChange1 = new UserChange(null, ActionUser.AGREGO_CLIENTE, technicians.get(0),clients.get(0), null);
        UserChange userChange2 = new UserChange(null, ActionUser.MODIFICO_DATOS_CLIENTE, technicians.get(1),clients.get(0), null);
        UserChange userChange3 = new UserChange(null, ActionUser.AGREGO_CLIENTE, technicians.get(2),clients.get(0), null);
        UserChange userChange4 = new UserChange(null, ActionUser.DESACTIVO_CUENTA_CLIENTE, technicians.get(0),clients.get(3), null);
        UserChange userChange5 = new UserChange(null, ActionUser.MODIFICO_DATOS_CLIENTE, technicians.get(1),clients.get(4), null);
        userChangeRepository.saveAll(List.of(userChange1, userChange2, userChange3, userChange4, userChange5));
    }
}
