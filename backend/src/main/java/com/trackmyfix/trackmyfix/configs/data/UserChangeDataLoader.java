package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.entity.UserChange;
import com.trackmyfix.trackmyfix.repository.ActionUserRepository;
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
    private ActionUserRepository actionUserRepository;
    private TechnicianRepository technicianRepository;
    private ClientRepository clientRepository;

    public void load() {
        List<ActionUser> actionUsers = (List<ActionUser>) this.actionUserRepository.findAll();
        List<Technician> technicians = (List<Technician>) this.technicianRepository.findAll();
        List<Client> clients = (List<Client>) this.clientRepository.findAll();

        UserChange userChange1 = new UserChange(null, actionUsers.get(0), clients.get(1), technicians.get(0), null);
        UserChange userChange2 = new UserChange(null, actionUsers.get(1), clients.get(2), technicians.get(1), null);
        UserChange userChange3 = new UserChange(null, actionUsers.get(2), clients.get(3), technicians.get(2), null);
        UserChange userChange4 = new UserChange(null, actionUsers.get(3), clients.get(4), technicians.get(0), null);
        UserChange userChange5 = new UserChange(null, actionUsers.get(0), clients.get(5), technicians.get(1), null);
        userChangeRepository.saveAll(List.of(userChange1, userChange2, userChange3, userChange4, userChange5));
    }
}
