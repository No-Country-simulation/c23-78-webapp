package com.trackmyfix.trackmyfix.configs.auth;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository<User> userRepository;
    private final UserRepository<Admin> adminRepository;
    private final UserRepository<Technician> technicianRepository;
    private final UserRepository<Client> clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));
        String userPass = null;
        switch (user.getRole()) {
            case ADMIN -> userPass = adminRepository.findById(user.getId()).get().getPassword();
            case TECHNICIAN -> userPass = technicianRepository.findById(user.getId()).get().getPassword();
            case CLIENT -> userPass = clientRepository.findById(user.getId()).get().getPassword();
        }
        assert userPass != null;
        Set<GrantedAuthority> authority = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UserJwtData(
                user.getId(),
                user.getEmail(),
                userPass,
                authority
        );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> this.loadUserByUsername(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
