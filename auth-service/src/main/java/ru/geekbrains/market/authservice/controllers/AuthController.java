package ru.geekbrains.market.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.AuthRequest;
import ru.geekbrains.market.api.dtos.AuthResponse;
import ru.geekbrains.market.api.dtos.DetailsUserDto;
import ru.geekbrains.market.api.dtos.UserDetailsForRegistrationDto;
import ru.geekbrains.market.api.exceptions.MarketError;
import ru.geekbrains.market.authservice.model.Role;
import ru.geekbrains.market.authservice.model.User;
import ru.geekbrains.market.authservice.services.RolesService;
import ru.geekbrains.market.authservice.services.UserService;
import ru.geekbrains.market.authservice.utils.JwtTokenUtil;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RolesService rolesService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PutMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody User newUser) {
        if (userService.isExistsUser(newUser.getUsername()) || userService.isExistsEmail(newUser.getEmail())) {
            return new ResponseEntity<>(new MarketError("This username or E-mail is occupied"), HttpStatus.UNAUTHORIZED);
        }
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        Role role = rolesService.getRoleWithUsersRights();
        user.setRoles(Collections.singletonList(role));
        user.setDetailsUser(newUser.getDetailsUser());
        userService.save(user);
        AuthRequest request = new AuthRequest(newUser.getUsername(), newUser.getPassword());
        return createAuthToken(request);
    }


    @PostMapping("")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
