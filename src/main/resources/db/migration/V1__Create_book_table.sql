create table if not exists public.book
(
    id             serial
        primary key,
    available      boolean,
    name           varchar(255),
    price          double precision not null,
    published_date date,
    author_name    varchar(255)
);