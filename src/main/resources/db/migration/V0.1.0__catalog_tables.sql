create table author
(
    id   bigserial primary key,
    name varchar(255) not null,
    unique (name)
);

create table category
(
    id   bigserial primary key,
    name varchar(255) not null,
    unique (name)
);

create type book_status as enum ('AVAILABLE', 'BORROWED');

create table book
(
    id          bigserial primary key,
    title       varchar(255) not null,
    author_id   bigint       not null,
    category_id bigint       not null,
    isbn        varchar(13)  not null,
    status      book_status  not null,
    foreign key (author_id) references author (id),
    foreign key (category_id) references category (id),
    unique (isbn)
);
