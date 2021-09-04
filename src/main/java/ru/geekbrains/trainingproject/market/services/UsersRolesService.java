package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.UsersRoles;
import ru.geekbrains.trainingproject.market.repositories.UsersRolesRepository;

@Service
@RequiredArgsConstructor
public class UsersRolesService {
    private final UsersRolesRepository usersRolesRepository;

    public void save(UsersRoles usersRoles) {
        usersRolesRepository.save(usersRoles);
    }
}
