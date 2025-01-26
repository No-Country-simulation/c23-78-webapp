package com.trackmyfix.trackmyfix.Dto.Request;

import jakarta.validation.constraints.NotNull;

public class RefreshTokenRequestDTO {
    @NotNull
    private String refresh_token;
    @NotNull
    private GrantType grant_type;
}
