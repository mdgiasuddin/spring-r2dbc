create table if not exists public.university
(
    id   serial primary key,
    name varchar(50) not null
);

create table if not exists public.student
(
    id            serial primary key,
    name          varchar(50) not null,
    university_id integer     not null
        constraint fk__student_university references public.university (id)
);

