create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('Food');

CREATE TABLE products
(
    id          bigserial primary key,
    title       VARCHAR(255),
    price       INTEGER,
    category_id bigint references categories (id)
);

insert into products (title, price, category_id)
values ('bread', 5, 1),
       ('milk', 20, 1),
       ('sausage', 21, 1),
       ('sour', 15, 1),
       ('cream', 150, 1),
       ('kefir', 250, 1),
       ('meat', 650, 1),
       ('nuts', 60, 1),
       ('tea', 350, 1),
       ('coffee', 750, 1),
       ('cocoa cola', 99, 1),
       ('sprite', 98, 1),
       ('fanta', 97, 1),
       ('butter', 50, 1),
       ('oranges', 88, 1),
       ('lemons', 122, 1),
       ('chips', 29, 1),
       ('beer', 800, 1),
       ('fish', 1200, 1),
       ('pasta', 85, 1);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    id bigserial,
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');


insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);