package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.entity.UserChange;
import com.trackmyfix.trackmyfix.repository.ActionUserRepository;
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

    public void load() {
        List<ActionUser> actionUsers = (List<ActionUser>) this.actionUserRepository.findAll();
        List<Technician> technicians = (List<Technician>) this.technicianRepository.findAll();
        UserChange userChange1 = new UserChange(null, actionUsers.get(0), technicians.get(0), null);
        UserChange userChange2 = new UserChange(null, actionUsers.get(1), technicians.get(1), null);
        UserChange userChange3 = new UserChange(null, actionUsers.get(2), technicians.get(2), null);
        UserChange userChange4 = new UserChange(null, actionUsers.get(3), technicians.get(0), null);
        UserChange userChange5 = new UserChange(null, actionUsers.get(0), technicians.get(1), null);
        userChangeRepository.saveAll(List.of(userChange1, userChange2, userChange3, userChange4, userChange5));
    }
}
