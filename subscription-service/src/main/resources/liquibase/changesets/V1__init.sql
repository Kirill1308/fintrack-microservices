CREATE TABLE IF NOT EXISTS subscriptions
(
    id               BIGSERIAL PRIMARY KEY,
    user_id          BIGINT       NOT NULL,
    tier             VARCHAR(255) NOT NULL,
    plan             VARCHAR(255) NOT NULL,
    status           VARCHAR(255) NOT NULL,
    start_date       DATE         NOT NULL,
    end_date         DATE         NOT NULL,
    auto_renew       BOOLEAN DEFAULT FALSE,
    grace_period_end DATE,
    current_price    DECIMAL(10, 2)
);
