package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.security.AuthRequest;
import ru.geekbrains.trainingproject.market.dtos.security.AuthResponse;
import ru.geekbrains.trainingproject.market.dtos.security.UserDetailsForRegistrationDto;
import ru.geekbrains.trainingproject.market.exceptions.MarketError;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.model.User;
import ru.geekbrains.trainingproject.market.services.RolesService;
import ru.geekbrains.trainingproject.market.services.UserService;
import ru.geekbrains.trainingproject.market.utils.JwtTokenUtil;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RolesService rolesService;

    @PostMapping("")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PutMapping("")
    public ResponseEntity<?> createNewUser(@RequestBody UserDetailsForRegistrationDto userRegDto) {
        if (userService.isExistsUser(userRegDto.getUsername())||userService.isExistsEmail(userRegDto.getEmail())) {
            return new ResponseEntity<>(new MarketError("This username or E-mail is occupied"), HttpStatus.UNAUTHORIZED);
        }

        User user = new User();
        user.setUsername(userRegDto.getUsername());
        user.setEmail(userRegDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegDto.getPassword()));
        Role role = rolesService.getRoleWithUsersRights();
        user.setRoles(Collections.singletonList(role));
        userService.save(user);
        AuthRequest request = new AuthRequest(userRegDto.getUsername(), userRegDto.getPassword());
        return createAuthToken(request);
    }

    @GetMapping("")
    public String createTmpId() {
        return userService.createTmpId();
    }
}
