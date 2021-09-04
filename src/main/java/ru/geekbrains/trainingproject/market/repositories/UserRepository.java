package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);
    public Boolean existsAllByUsernameEquals(String username);
    public Boolean existsAllByEmailEquals(String email);

}
