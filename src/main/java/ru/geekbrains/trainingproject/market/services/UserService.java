package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.model.User;
import ru.geekbrains.trainingproject.market.repositories.UserRepository;
import ru.geekbrains.trainingproject.market.utils.UserDataFromHttpRequestUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
//    private final DetailsUser detailsUser;
private final UserDataFromHttpRequestUtil userDataFromHttpRequestUtil;

    public Optional<User> findUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " is not found.")
        );
        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(),
                mapRolesTuAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesTuAuthorities(Collection<Role> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public boolean isExistsUser(String usrname) {
        return userRepository.existsAllByUsernameEquals(usrname);
    }

    public boolean isExistsEmail(String email) {
        return userRepository.existsAllByEmailEquals(email);
    }

    public String createTmpId() {
        return String.valueOf(System.currentTimeMillis());
    }

    public Optional<User> getUserUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<Order> getOrderByCurrentUser() {
        String currentUserName= userDataFromHttpRequestUtil.getUserName();
        User user = userRepository.findByUsername(currentUserName).get();
        return user.getOrderList();
    }
}

