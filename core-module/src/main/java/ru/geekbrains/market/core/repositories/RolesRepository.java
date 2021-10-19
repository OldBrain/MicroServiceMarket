package ru.geekbrains.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.core.model.Role;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(String name);
}
