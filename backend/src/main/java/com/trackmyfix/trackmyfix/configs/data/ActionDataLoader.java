package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.repository.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ActionDataLoader {
    private ActionRepository actionRepository;

    public void load() {
        Action action1 = new Action(null, "CREATE");
        Action action2 = new Action(null, "READ");
        Action action3 = new Action(null, "UPDATE");
        Action action4 = new Action(null, "DELETE");
        Action action5 = new Action(null, "EXPORT");
        actionRepository.saveAll(List.of(action1, action2, action3, action4, action5));
    }

}
