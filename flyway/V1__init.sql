create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

CREATE TABLE products
(
    id          bigserial primary key,
    title       VARCHAR(255),
    price       numeric(8,2),
    category_id bigint references categories (id)
);

create table comments
(
    id          bigserial primary key,
    username    VARCHAR(25),
    content     VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    product_id bigserial not null
);

insert into comments(username, content, product_id)
values ('admin','Просто супер. Это хлеб моей мечты',1),
       ('admin','Отвратительный товар не советую',2),
       ('user','Это хлеб из отрубей, и прочих отбросов!',1);





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
    price      numeric(8,2),
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
    price_per_product numeric(8,2),
    price             numeric(8,2),
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);






insert into products (title, price, category_id)
values ('bread', 5.5, 1),
       ('milk', 20.56, 1),
       ('sausage', 21.26, 1),
       ('sour', 15.00, 1),
       ('cream', 150.00, 1),
       ('kefir', 250.66, 1),
       ('meat', 650.55, 1),
       ('nuts', 60.01, 1),
       ('tea', 350.02, 1),
       ('coffee', 750.08, 1),
       ('cocoa cola', 99.55, 1),
       ('sprite', 98.66, 1),
       ('fanta', 97.11, 1),
       ('butter', 50.15, 1),
       ('oranges', 88.14, 1),
       ('lemons', 122.44, 1),
       ('chips', 29.99, 1),
       ('beer', 800.01, 1),
       ('fish', 1200.11, 1),
       ('pasta', 85.11, 1);

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
