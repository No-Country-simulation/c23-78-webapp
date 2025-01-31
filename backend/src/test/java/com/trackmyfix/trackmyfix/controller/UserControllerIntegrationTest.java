package com.trackmyfix.trackmyfix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

}
