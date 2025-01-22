package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.entity.Admin;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.entity.User;
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
        log.info("Line 32: User: "+user);
        String userPass = null;
        switch (user.getRole()) {
            case ADMIN -> userPass = adminRepository.findById(user.getId()).get().getPassword();
            case TECHNICIAN -> userPass = technicianRepository.findById(user.getId()).get().getPassword();
            case CLIENT -> userPass = clientRepository.findById(user.getId()).get().getPassword();
        }
        Set<GrantedAuthority> authority = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
        System.out.println("Authority from Db: "+authority);
        log.info("Line 40: User Password: "+userPass);
        assert userPass != null;
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                userPass,
                true,
                true,
                true,
                true,
                authority
        );
    }
}
