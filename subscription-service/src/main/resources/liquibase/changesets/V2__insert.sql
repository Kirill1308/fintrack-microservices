INSERT INTO subscriptions (user_id, tier, plan, status, start_date, end_date, auto_renew, grace_period_end, current_price)
VALUES (1, 'BASIC', 'ANNUAL', 'ACTIVE', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 month', TRUE, NULL, NULL),
       (2, 'PRO', 'MONTHLY', 'ACTIVE', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 month', TRUE, NULL, NULL),
       (3, 'PREMIUM', 'QUARTERLY', 'ACTIVE', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 month', TRUE, NULL, NULL),
       (4, 'PREMIUM', 'ANNUAL', 'ACTIVE', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 month', TRUE, NULL, NULL);
