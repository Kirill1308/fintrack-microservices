CREATE TABLE IF NOT EXISTS collaborators
(
    id        BIGSERIAL PRIMARY KEY,
    email     VARCHAR NOT NULL UNIQUE,
    wallet_id BIGINT  NOT NULL,
    role      VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS wallet_invitations
(
    id        BIGSERIAL PRIMARY KEY,
    wallet_id BIGINT  NOT NULL,
    email     VARCHAR NOT NULL UNIQUE,
    token     VARCHAR NOT NULL UNIQUE,
    role      VARCHAR NOT NULL
);
