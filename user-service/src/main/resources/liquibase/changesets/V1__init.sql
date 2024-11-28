CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL,
    name     VARCHAR(255) NOT NULL,
    avatar   VARCHAR(255)
);

create table if not exists password_reset_tokens
(
    id          BIGSERIAL PRIMARY KEY,
    token       VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP    NOT NULL
);
