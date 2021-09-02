package ru.geekbrains.trainingproject.market.dtos.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@NoArgsConstructor
@Data

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
