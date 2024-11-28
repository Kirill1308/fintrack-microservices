CREATE TABLE IF NOT EXISTS transactions
(
    id           BIGSERIAL PRIMARY KEY,
    owner_id     BIGINT         NOT NULL,
    wallet_id    BIGINT         NOT NULL,
    type         VARCHAR        NOT NULL,
    category_id  BIGINT         NOT NULL,
    payment      VARCHAR        NOT NULL,
    amount       DECIMAL(15, 2) NOT NULL,
    note         TEXT,
    date_created DATE           NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    type    VARCHAR NOT NULL,
    user_id BIGINT  NOT NULL
);
