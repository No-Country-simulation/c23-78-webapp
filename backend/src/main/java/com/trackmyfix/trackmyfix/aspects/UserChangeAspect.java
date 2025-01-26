package com.trackmyfix.trackmyfix.aspects;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify;
import com.trackmyfix.trackmyfix.entity.User;
import com.trackmyfix.trackmyfix.entity.UserJwtData;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.UserChangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@Aspect
@AllArgsConstructor
public class UserChangeAspect {

    private UserChangeService userChangeService;
    private UserRepository<User> userRepository;

    @After(value = "@annotation(com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify)")
    public void afterUserChange(JoinPoint joinPoint) throws Throwable {

        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var annotation = method.getAnnotation(UserChangeNotify.class);

        UserJwtData jwtUser = null;
        AtomicReference<UserRequestDTO> user = new AtomicReference<>();
        AtomicLong deletedId = new AtomicLong(0L);

        Arrays.stream(joinPoint.getArgs())
            .forEach(arg -> {
                if (arg instanceof UserRequestDTO) {
                    user.set((UserRequestDTO) arg);
                }
                if(arg instanceof Long) {
                    deletedId.set((Long) arg);
                }
        });
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            log.info(auth.toString());
            jwtUser = (UserJwtData) auth.getPrincipal();
            if (auth.isAuthenticated()) {
                switch(annotation.actionUser()){
                    case MODIFICO_DATOS_CLIENTE -> {
                        if(user.get() != null) {
                            userChangeService.save(annotation.actionUser(), jwtUser.getId(), user.get().getId());
                        }
                    }
                    case DESACTIVO_CUENTA_CLIENTE, ACTIVO_CUENTA_CLIENTE, ELIMINO_CLIENTE -> {
                        if (deletedId.get() != 0L) {
                            userChangeService.save(annotation.actionUser(), jwtUser.getId(), deletedId.get());
                        }
                    }
                    case AGREGO_CLIENTE -> {
                        User newUser = userRepository.findByEmail(user.get().getEmail()).orElseThrow(()-> new UserNotFoundException("Email not found"));
                        userChangeService.save(annotation.actionUser(), jwtUser.getId(), newUser.getId());
                    }
                    default -> log.error("Action UserChange "+annotation.actionUser()+" doesn't exist");
                }
            }
        }

        log.info("annotation params: {}", annotation.actionUser());

    }
}
