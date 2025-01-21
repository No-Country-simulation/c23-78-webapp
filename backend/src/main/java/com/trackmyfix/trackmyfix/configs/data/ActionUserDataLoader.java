package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.repository.ActionUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ActionUserDataLoader {
    private ActionUserRepository actionUserRepository;

    public void load(){
        ActionUser actionUser1 = new ActionUser(null,"CREATE");
        ActionUser actionUser2 = new ActionUser(null,"READ");
        ActionUser actionUser3 = new ActionUser(null,"UPDATE");
        ActionUser actionUser4 = new ActionUser(null,"DELETE");
        ActionUser actionUser5 = new ActionUser(null,"EXPORT");
        actionUserRepository.saveAll(List.of(actionUser1,actionUser2,actionUser3,actionUser4,actionUser5));
    }

}
