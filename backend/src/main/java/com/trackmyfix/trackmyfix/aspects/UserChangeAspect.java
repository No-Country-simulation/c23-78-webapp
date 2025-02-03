package com.trackmyfix.trackmyfix.aspects;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify;
import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.User;
import com.trackmyfix.trackmyfix.entity.UserJwtData;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.Impl.UserChangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static com.trackmyfix.trackmyfix.entity.Role.CLIENT;
import static com.trackmyfix.trackmyfix.entity.Role.TECHNICIAN;

@Slf4j
@Component
@Aspect
@AllArgsConstructor
public class UserChangeAspect {

    private UserChangeService userChangeService;
    private UserRepository<User> userRepository;

    private final AtomicReference<UserRequestDTO> user = new AtomicReference<>();
    private final AtomicLong deletedId = new AtomicLong(0L);
    private static ActionUser actionUser;

    @Before(value = "@annotation(com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify)")
    public void beforeUserChange(JoinPoint joinPoint) {

        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var annotation = method.getAnnotation(UserChangeNotify.class);

        Arrays.stream(joinPoint.getArgs())
                .forEach(arg -> {
                    if (arg instanceof UserRequestDTO) {
                        this.user.set((UserRequestDTO) arg);
                    }
                    if(arg instanceof Long) {
                        this.deletedId.set((Long) arg);
                    }
                });
        actionUser = annotation.actionUser();

    }

    @AfterReturning(value = "@annotation(com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify)")
    public void afterUserChange(JoinPoint joinPoint) throws Throwable {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            if (auth.getPrincipal() != "anonymousUser") {
                String WARN_MESSAGE = "Only technicians and client roles can interact with userChanges in Db. log not saved";
                UserJwtData jwtUser = (UserJwtData) auth.getPrincipal();
                boolean isTechnician = Objects.equals(jwtUser.getAuthorities(), Set.of(new SimpleGrantedAuthority(TECHNICIAN.name())));
                if (isTechnician) {
                    switch(actionUser){
                        case MODIFICO_DATOS_CLIENTE -> {
                            if(user.get() != null) {
                                if(user.get().getRole() == CLIENT) {
                                    userChangeService.save(actionUser, jwtUser.getId(), user.get().getId());
                                } else {
                                    log.warn(WARN_MESSAGE);
                                }
                            }
                        }
                        case DESACTIVO_CUENTA_CLIENTE, ACTIVO_CUENTA_CLIENTE, ELIMINO_CLIENTE -> {
                            if (deletedId.get() != 0L) {
                                User found = userRepository.findById(deletedId.get()).orElseThrow(()-> new UserNotFoundException("user not found"));
                                if (found.getRole() == CLIENT) {
                                    userChangeService.save(
                                            found.getActive()
                                                    ? ActionUser.ACTIVO_CUENTA_CLIENTE
                                                    : ActionUser.DESACTIVO_CUENTA_CLIENTE,
                                            jwtUser.getId(),
                                            deletedId.get());
                                } else {
                                    log.warn(WARN_MESSAGE);
                                }
                            }
                        }
                        case AGREGO_CLIENTE -> {
                            User newUser = userRepository.findByEmail(user.get().getEmail()).orElseThrow(()-> new UserNotFoundException("Email not found"));
                            userChangeService.save(actionUser, jwtUser.getId(), newUser.getId());
                        }
                        default -> log.error("Action UserChange "+actionUser+" doesn't exist");
                    }
                } else {
                    log.warn("line 99: "+WARN_MESSAGE);
                }
            }
        } else {
            log.info("new client registered, with anonymous role");
        }
    }

    @AfterThrowing(value = "@annotation(com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify)")
    public void afterThrowingHandler(JoinPoint joinPoint, Exception ex){
        log.error(ex.getMessage());
    }
}
