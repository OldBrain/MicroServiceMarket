CREATE TABLE product
(
    id    bigserial,
    title VARCHAR(255),
    price INTEGER,
    PRIMARY KEY (id)
);



insert into product (title, price)
values ('bread', 5),
       ('milk', 20),
       ('sausage', 21),
       ('sour', 15),
       ('cream', 150),
       ('kefir', 250),
       ('meat', 650),
       ('nuts', 60),
       ('tea', 350),
       ('coffee', 750),
       ('cocoa cola', 99),
       ('sprite', 98),
       ('fanta', 97),
       ('butter', 50),
       ('oranges', 88),
       ('lemons', 122),
       ('chips', 29),
       ('beer', 800),
       ('fish', 1200),
       ('pasta', 85);

CREATE TABLE user
(
    id   bigint,
    user_mame varchar(255)
);

insert into user (user_mame)
values ('Peter'),
       ('Ben');