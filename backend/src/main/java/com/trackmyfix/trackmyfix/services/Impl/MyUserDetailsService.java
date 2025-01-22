package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
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
        Set<GrantedAuthority> authority = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
        assert userPass != null;
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                userPass,
//                true,
//                true,
//                true,
//                true,
//                authority
//        );
        return new UserJwtData(
                user.getId(),
                user.getEmail(),
                userPass,
                authority
        );
    }
}
