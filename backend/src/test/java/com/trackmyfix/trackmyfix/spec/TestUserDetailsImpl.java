package com.trackmyfix.trackmyfix.spec;

import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.entity.User;
import com.trackmyfix.trackmyfix.entity.UserJwtData;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Objects;

@Service
@Primary
@Profile("test")
public class TestUserDetailsImpl implements UserDetailsService {
    public static final String TECHNICIAN_USER = "tech1@example.com";

    private UserJwtData getTechnicianUser() {
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(Role.TECHNICIAN.name());
        return new UserJwtData(2L, TECHNICIAN_USER, "techpassword1", Collections.singletonList(role));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (Objects.equals(username, TECHNICIAN_USER))
            return getTechnicianUser();
        throw new UsernameNotFoundException(username);
    }
}