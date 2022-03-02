create sequence hibernate_sequence start 2 increment 1;

create table ordr (
    id int8 not null,
    client_address varchar(255),
    client_phone_number varchar(255),
    order_comment varchar(255),
    order_status varchar(255),
    restaurant_address varchar(255),
    restaurant_comment varchar(2048),
    restaurant_name varchar(255),
    restaurant_phone_number varchar(255),
    author_id int8,
    courier_id int8,
    primary key (id)
);

create table usr (
    id int8 not null,
    active boolean not null,
    address varchar(255),
    comment varchar(2048),
    displayed_name varchar(255),
    password varchar(255) not null,
    phone_number varchar(255),
    role varchar(255) not null,
    username varchar(255) not null,
    working boolean not null,
    primary key (id));

alter table if exists ordr
    add constraint ordr_author_fk
    foreign key (author_id) references usr;

alter table if exists ordr
    add constraint ordr_courier_fk
    foreign key (courier_id) references usr;
