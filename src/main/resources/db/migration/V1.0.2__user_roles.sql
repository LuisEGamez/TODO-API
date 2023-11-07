CREATE TABLE IF NOT EXISTS user_roles
(
    user_id    uuid         not null,
    role_id    uuid    not null,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES public.users (id),
    CONSTRAINT fk_role_id
        FOREIGN KEY (role_id)
            REFERENCES public.roles (id)
);
