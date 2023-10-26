CREATE TABLE todo(
    id uuid not null primary key,
    title varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    started_at timestamp,
    finished_at timestamp,
    description varchar(255) not null,
    deleted boolean not null
);