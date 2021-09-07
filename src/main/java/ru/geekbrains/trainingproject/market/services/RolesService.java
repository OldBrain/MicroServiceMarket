package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.repositories.RolesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;

    public Optional<Role> findByName(String name) {
        return rolesRepository.findByName(name);
    }

    public Role getRoleWithUsersRights() {
      return findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Role <ROLE_USER> not exists, in list roles"));
    }
}
