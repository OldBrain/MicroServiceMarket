package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.Role;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(String name);
}
