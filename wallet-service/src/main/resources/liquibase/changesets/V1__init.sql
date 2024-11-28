CREATE TABLE wallets
(
    id       BIGSERIAL PRIMARY KEY,
    owner_id BIGINT  NOT NULL,
    name     VARCHAR NOT NULL,
    currency VARCHAR NOT NULL
);
