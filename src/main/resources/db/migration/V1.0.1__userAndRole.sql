CREATE TABLE IF NOT EXISTS users
(
    id         uuid         not null primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    email      varchar(255) not null,
    name       varchar(255) not null,
    password   varchar(255) not null,
    enabled    boolean      not null,
    delete     boolean      not null
);

CREATE TABLE IF NOT EXISTS roles
(
    id   uuid         not null primary key,
    role varchar(255) not null
);