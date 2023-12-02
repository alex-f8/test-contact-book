-- users
create table users
(
    id        bigint primary key,
    username  varchar(450) not null,
    password  varchar(100) not null,
    authority varchar(20)  not null default 'user',
    full_name varchar(200) not null,
    is_active boolean,
    unique (username)
);
create sequence users_seq increment 1 start with 1;

insert into users (id, username, password, authority, full_name, is_active)
values (nextval('users_seq'), 'admin', '$2a$05$IIEfO9IZWZ6ucTBtsRMpKuK.wlXgnu16y9dnbfkBKrBhDc2Kiyct6', 'ADMIN',
        'super admin', true),
       (nextval('users_seq'), 'user', '$2a$05$IIEfO9IZWZ6ucTBtsRMpKuK.wlXgnu16y9dnbfkBKrBhDc2Kiyct6', 'USER',
        'user user', true),
       (nextval('users_seq'), 'test', '$2a$05$IIEfO9IZWZ6ucTBtsRMpKuK.wlXgnu16y9dnbfkBKrBhDc2Kiyct6', 'USER',
        'test test', true);

create table contacts
(
    id         bigint primary key,
    first_name varchar,
    last_name  varchar,
    phone      varchar(30) not null,
    email      varchar(200),
    address    varchar,
    user_id    bigint      not null,
    foreign key (user_id) references users (id)
);
create sequence contacts_seq increment 1 start with 1;


insert into contacts (id, first_name, last_name, phone, email, address, user_id)
values (nextval('contacts_seq'), 'Some1', 'One1', '+123', 'some.one1@email.com', 'georgia tbilisi rustaveli 1', 2),
       (nextval('contacts_seq'), 'Some2', 'One2', '+123', 'some.one2@email.com', 'georgia tbilisi rustaveli 1', 2),
       (nextval('contacts_seq'), 'Some3', 'One3', '+123', 'some.one3@email.com', 'georgia tbilisi rustaveli 1', 3),
       (nextval('contacts_seq'), 'Some4', 'One4', '+123', 'some.one4@email.com', 'georgia tbilisi rustaveli 1', 3)
/*
avtorization

first_name
last_name
phone
email
address

create contact
get contacts paging
delete contacts
update contacts*/