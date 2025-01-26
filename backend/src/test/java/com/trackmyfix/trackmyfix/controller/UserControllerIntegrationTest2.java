package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.configs.auth.JwtFilter;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.services.Impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(controllers = UserController.class)
public class UserControllerIntegrationTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userServiceMock;

    @MockitoBean
    private JwtFilter jwtFilterMock;

    private UserRequestDTO sampleAdminRequest;

    @BeforeEach
    void setUp() {
        sampleAdminRequest = UserRequestDTO.builder()
                .role(Role.ADMIN)
                .name("NombreAdmin1")
                .lastName("Apellido")
                .dni("44350651")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd@asd.com")
                .password("12345678Nmmm")
                .build();
    }

    // Write your tests here
    @DisplayName("Test for Registering Admin controller")
    @Test
    public void testRegisterAdmin() throws Exception {
        //GIVEN
        UserResponseDTO adminResponse = UserResponseDTO.builder()
                .name("NombreAdmin1")
                .lastName("Apellido")
                .dni("44350651")
                .address("blanco 5126")
                .phone("1122540454")
                .email("asd@asd.com")
                .role(Role.ADMIN)
                .build();

        //given(userServiceMock.save(any(UserRequestDTO.class))).willReturn(adminResponse);

        mockMvc.perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Jackson2ObjectMapperBuilder().build().writeValueAsString(sampleAdminRequest))
                )
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name").value("NombreAdmin1"))
//                .andExpect(jsonPath("$.lastName", is("Apellido")))
//                .andExpect(jsonPath("$.email", is("asd2@asd.com")))
//                .andExpect(jsonPath("$.role", is("ADMIN")));
    }

    @Test
    void givenRequestIsAnonymous_whenGetGreet_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/work-order").with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testLogin() throws Exception {
        //GIVEN
        mockMvc
                .perform(
                        formLogin("/user/login")
                                .user("jindrg@gmail.com")
                                .password("adminpassword1")
                ).andExpect(status().isOk());
    }
}
