create table if not exists public._user
(
    id       serial
        primary key,
    username varchar(30) unique not null,
    password varchar(60)        not null,
    active   boolean default false,
    role     varchar(10)        not null
);