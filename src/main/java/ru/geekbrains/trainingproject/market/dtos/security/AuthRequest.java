package ru.geekbrains.trainingproject.market.dtos.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {
    private String userName;
    private String password;
}
