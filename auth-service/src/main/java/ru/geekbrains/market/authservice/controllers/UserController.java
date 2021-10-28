package ru.geekbrains.market.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.api.dtos.ProfileDto;
import ru.geekbrains.market.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.authservice.converter.ModelToDtoConverter;
import ru.geekbrains.market.authservice.model.User;
import ru.geekbrains.market.authservice.services.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelToDtoConverter converter;
    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(@RequestHeader String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + username));
        return new ProfileDto(user.getUsername(), user.getEmail(), Collections.singletonList(converter.detailsUserToDto(user.getDetailsUser())));
    }
}
