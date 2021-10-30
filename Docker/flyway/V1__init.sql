create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

CREATE TABLE products
(
    id          bigserial primary key,
    title       VARCHAR(255),
    price       INTEGER,
    category_id bigint references categories (id)
);

insert into categories (title)
values ('Food');

create table if not exists user_details
(
    id         bigserial primary key,
    last_name  varchar(100),
    patronymic varchar(100),
    first_name varchar(100),
    phone      varchar(20),
    address    varchar(255)
);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    details_id bigint,
    FOREIGN KEY (details_id) references user_details (id)
);

create table order_status
(
    id    bigserial primary key,
    title varchar(20)
);

create table if not exists orders
(
    id         bigserial primary key,
    username   varchar(30) references users (username),
    last_name  varchar(100),
    patronymic varchar(100),
    first_name varchar(100),
    phone      varchar(20),
    address    varchar(255),
    price        integer,
    status_id  bigserial,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (status_id) references order_status (id)
);

create table order_items
(
    id                bigserial primary key,
    order_id          bigint references orders (id),
    product_id        bigserial references products (id),
    quantity          integer,
    price_per_product integer,
    price             integer,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
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

insert into user_details(last_name, patronymic, first_name, phone, address)
VALUES ('Татьяна', 'Петровна', 'Юзерова', '25-25-25', 'Астрахань'),
       ('Сергей', 'Иванович', 'Админов', '25-25-25', 'Астрахань');

insert into order_status(title)
values ('сформирован'),
       ('подтвержден'),
       ('оплачен'),
       ('на сборке'),
       ('отправлен'),
       ('получен покупателем'),
       ('выполнен');

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    id      bigserial,
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email, details_id)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com', 1),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com', 2);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

insert into orders(last_name, patronymic, first_name, phone, address, price, username, status_id)
values ('Владимир', 'Иванович', 'Тестов', '+7(905)555-555', 'Москва, Кремль', '10099', 'admin', 1);

insert into order_items (order_id, product_id, quantity, price_per_product, price)
values (1, 1, 4, 25, 100);