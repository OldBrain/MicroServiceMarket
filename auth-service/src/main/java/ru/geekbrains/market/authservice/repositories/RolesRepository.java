package ru.geekbrains.market.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.authservice.model.Role;
import ru.geekbrains.market.authservice.model.User;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
