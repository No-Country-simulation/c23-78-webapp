package com.trackmyfix.trackmyfix.configs;

import com.trackmyfix.trackmyfix.configs.data.*;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(
            UserDataLoader userDataLoader,
            OrderDataLoader orderDataLoader,
            TypeDataLoader typeDataLoader,
            StateDataLoader stateDataLoader,
            ActionDataLoader actionDataLoader,
            DeviceDataLoader deviceDataLoader,
            MovementDataLoader movementDataLoader) {
        return args -> {
            userDataLoader.load();
            orderDataLoader.load();
            typeDataLoader.load();
            stateDataLoader.load();
            actionDataLoader.load();
            deviceDataLoader.load();
            movementDataLoader.load();

        };
    }
}
