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

CREATE TABLE user
(
    id        bigint,
    user_mame varchar(255)
);

insert into user (user_mame)
values ('Peter'),
       ('Ben');