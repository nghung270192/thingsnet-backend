CREATE TABLE  IF NOT EXISTS users
(
    id              uuid   NOT NULL  CONSTRAINT users_pkey PRIMARY KEY,
    email           varchar(255) UNIQUE NOT NULL,
    first_name      varchar(255),
    last_name       varchar(255),
    password       varchar(255),
    created_time    bigint NOT NULL,
    additional_info varchar,
    enable          bool,
    phone           varchar(255)
);

CREATE TABLE  IF NOT EXISTS roles
(
    id   uuid                NOT NULL
        CONSTRAINT roles_pkey PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE privileges
(
    id   uuid                NOT NULL
        CONSTRAINT privileges_pkey PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    PRIMARY KEY (user_id, role_id),

    /*Xoa user id or role is => row in users_roles delete too*/

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE  IF NOT EXISTS roles_privileges
(
    role_id      uuid NOT NULL,
    privilege_id uuid NOT NULL,
    PRIMARY KEY (role_id, privilege_id),

    /*Xoa user id or role is => row in users_roles delete too*/
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (privilege_id) REFERENCES privileges (id) ON DELETE CASCADE
);
