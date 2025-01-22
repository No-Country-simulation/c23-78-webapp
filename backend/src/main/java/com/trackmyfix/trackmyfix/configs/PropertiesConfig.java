package com.trackmyfix.trackmyfix.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "configs/client_security.properties")
})
public class PropertiesConfig {
}
