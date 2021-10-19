package ru.geekbrains.market.core.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.modeldtos.UserPersonalAccountDto;
import ru.geekbrains.market.api.dtos.security.AuthRequest;
import ru.geekbrains.market.api.dtos.security.AuthResponse;
import ru.geekbrains.market.api.dtos.security.UserDetailsForRegistrationDto;
import ru.geekbrains.market.api.exeptions.MarketError;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;
import ru.geekbrains.market.core.model.Role;
import ru.geekbrains.market.core.model.User;
import ru.geekbrains.market.core.services.RolesService;
import ru.geekbrains.market.core.services.UserService;
import ru.geekbrains.market.core.utils.converters.ConverterDtoToModel;
import ru.geekbrains.market.core.utils.converters.ConverterModelToDto;
import ru.geekbrains.market.core.utils.JwtTokenUtil;

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
    private final ConverterModelToDto converterModelToDto;
    private final ConverterDtoToModel converterDtoToModel;

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
    public ResponseEntity<?> createNewUser(@RequestBody UserDetailsForRegistrationDto urd) {
        if (userService.isExistsUser(urd.getUsername())||userService.isExistsEmail(urd.getEmail())) {
            return new ResponseEntity<>(new MarketError("This username or E-mail is occupied"), HttpStatus.UNAUTHORIZED);
        }
        System.out.println(urd.getDetailsUserDto());

        User user = new User();
        user.setUsername(urd.getUsername());
        user.setEmail(urd.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(urd.getPassword()));
        Role role = rolesService.getRoleWithUsersRights();
        user.setRoles(Collections.singletonList(role));
        user.setDetailsUser(converterDtoToModel.detailsUserFromDto(urd.getDetailsUserDto()));
        userService.save(user);
        AuthRequest request = new AuthRequest(urd.getUsername(), urd.getPassword());
        return createAuthToken(request);
    }

    @GetMapping("/getid")
    public String createTmpId() {
        return userService.createTmpId();
    }

    @GetMapping("/{userName}")
    public UserPersonalAccountDto getUser(@PathVariable String userName) {
        User user= userService.getUserByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя с именем " + userName));
        return  converterModelToDto.userPersonalAccountToDto(user);
    }

//    @GetMapping("/orders/{userName}")
//    public ResponseEntity<?> getUserOrders(@PathVariable String userName) {
//        UserPersonalAccountDto personalAccountDto =new UserPersonalAccountDto(userService.getUserUserName(userName).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя с именем " + userName)));
//        return new ResponseEntity<>(personalAccountDto,HttpStatus.OK);
//    }
}
