CREATE TABLE IF NOT EXISTS daily_wallet_analytics
(
    id             BIGSERIAL PRIMARY KEY,
    wallet_id      BIGINT         NOT NULL,
    date           TIMESTAMP      NOT NULL,
    current_wallet_balance DECIMAL(10, 2) NOT NULL,
    total_income   DECIMAL(10, 2) NOT NULL,
    total_expenses DECIMAL(10, 2) NOT NULL
);
