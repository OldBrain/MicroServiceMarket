package ru.geekbrains.market.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.authservice.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsAllByUsernameEquals(String usrname);

    boolean existsAllByEmailEquals(String email);
}
