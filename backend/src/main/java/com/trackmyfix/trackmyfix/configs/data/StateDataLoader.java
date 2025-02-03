package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StateDataLoader {

    private final StateRepository stateRepository;

    public void load(){
        State state1 = new State(null,"RECEIVED");
        State state2 = new State(null,"REPAIRED");
        State state3 = new State(null,"DISPATCHED");
        State state4 = new State(null,"DELIVERED");
        State state5 = new State(null,"CANDELLED");

        stateRepository.saveAll(List.of(state1,state2,state3,state4,state5));
    }
}
