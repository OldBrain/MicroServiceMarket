package ru.geekbrains.trainingproject.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_roles")
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "role_id")
    private Long role_id;

}
