package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.trainingproject.market.dtos.security.AuthResponse;
import ru.geekbrains.trainingproject.market.dtos.security.UserDetailsForRegistrationDto;
import ru.geekbrains.trainingproject.market.exceptions.MarketError;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.model.User;
import ru.geekbrains.trainingproject.market.model.UsersRoles;
import ru.geekbrains.trainingproject.market.services.RolesService;
import ru.geekbrains.trainingproject.market.services.UserService;
import ru.geekbrains.trainingproject.market.services.UsersRolesService;
import ru.geekbrains.trainingproject.market.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reg")
public class RegController {
    private final UserService userService;
    private final UsersRolesService usersRolesService;
    private final RolesService rolesService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
/***
 * Хотел сразу вернуть токен при регистрации, что бы фронт мог сразу
 * залогинить нового юзера, но возвращает
 * MarketError("Incorrect username or password"). Так и не понял почему.
 *  Пришлось закоментить кусок кода и возвращать Boolean.
 * */
    @PutMapping("")
    public ResponseEntity<?> createNewUser(@RequestBody UserDetailsForRegistrationDto userRegDto) {
        if (userService.isExistsUser(userRegDto.getUsername())||userService.isExistsEmail(userRegDto.getEmail())) {
            return new ResponseEntity<>(new MarketError("This username or E-mail is occupied"), HttpStatus.UNAUTHORIZED);
        }

        User user = new User();
        user.setUsername(userRegDto.getUsername());
        user.setEmail(userRegDto.getEmail());
        user.setPassword(encryptPassword(userRegDto.getPassword()));
        userService.save(user);

        assignTheUserRoleToNewUser(user);

//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), userRegDto.getPassword()));
//        } catch (AuthenticationException e) {
//            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
//        }
//        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
//        String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponse(token));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private void assignTheUserRoleToNewUser(User user) {
        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUser_id(user.getId());
        Role rolesForUser = rolesService.findByName("ROLE_USER").
                orElseThrow(() -> new ResourceNotFoundException("Role <ROLE_USER> not exists, in list roles"));
        usersRoles.setRole_id(rolesForUser.getId());
        usersRolesService.save(usersRoles);
    }
}
