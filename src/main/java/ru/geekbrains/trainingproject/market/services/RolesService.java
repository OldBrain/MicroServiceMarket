package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.model.UsersRoles;
import ru.geekbrains.trainingproject.market.repositories.RolesRepository;
import ru.geekbrains.trainingproject.market.repositories.UsersRolesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;

    public Optional<Role> findByName(String name) {
        return rolesRepository.findByName(name);
    }
}
