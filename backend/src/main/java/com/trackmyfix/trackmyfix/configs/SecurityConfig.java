package com.trackmyfix.trackmyfix.configs;

import com.trackmyfix.trackmyfix.configs.auth.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import static com.trackmyfix.trackmyfix.entity.Role.TECHNICIAN;
import static com.trackmyfix.trackmyfix.entity.Role.ADMIN;
import static com.trackmyfix.trackmyfix.entity.Role.CLIENT;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;

    private static final String[] ADMIN_ROUTES = {"/**"};
    private static final String[] TECHNICIAN_ROUTES = {"/work-order/**", "/device/**"};
    private static final String[] CLIENT_ROUTES = {"/"};
    private static final String[] PUBLIC_ROUTES = {
            "/user/login",
            "/user/register",
            "/user/logout"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.addAllowedMethod("POST");
                    configuration.addAllowedMethod("GET");
                    configuration.addAllowedMethod("PUT");
                    configuration.addAllowedMethod("DELETE");
                    configuration.addAllowedMethod("OPTIONS");
                    configuration.addAllowedHeader("*");
                    configuration.addAllowedOrigin("*");
                    return configuration;
                }))

                .authorizeHttpRequests(request -> request
                        .requestMatchers(PUBLIC_ROUTES).permitAll()
                        .requestMatchers(GET,"/work-order/number/**").permitAll()
                        .requestMatchers(GET, "/user/profile").authenticated()
                        .requestMatchers(POST, "/user/**").hasAnyAuthority(TECHNICIAN.name(), ADMIN.name())
                        .requestMatchers(DELETE, "/user/**").hasAnyAuthority(TECHNICIAN.name(), ADMIN.name())
                        .requestMatchers(PUT, "/user/**").hasAnyAuthority(TECHNICIAN.name(), ADMIN.name())
                        .requestMatchers(TECHNICIAN_ROUTES).hasAnyAuthority(TECHNICIAN.name(), ADMIN.name())
                        .requestMatchers(ADMIN_ROUTES).hasAuthority(ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .logout(logout ->
                        logout.logoutUrl("/user/logout")
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                )
        );
        return http.build();
    }
}
