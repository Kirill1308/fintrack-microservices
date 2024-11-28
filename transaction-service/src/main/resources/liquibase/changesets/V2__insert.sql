INSERT INTO categories (name, type, user_id)
VALUES
    -- Income categories
    ('Business', 'SYSTEM', 4),
    ('Extra Income', 'SYSTEM', 4),
    ('Gifts', 'SYSTEM', 4),
    ('Insurance', 'SYSTEM', 4),
    ('Loan', 'SYSTEM', 4),
    ('Other', 'SYSTEM', 4),
    ('Parental Leave', 'SYSTEM', 4),
    ('Salary', 'SYSTEM', 4),

    -- Expense categories
    ('Bills and Fees', 'SYSTEM', 4),
    ('Beauty', 'SYSTEM', 4),
    ('Car', 'SYSTEM', 4),
    ('Education', 'SYSTEM', 4),
    ('Entertainment', 'SYSTEM', 4),
    ('Family and Personal', 'SYSTEM', 4),
    ('Food and Drink', 'SYSTEM', 4),
    ('Gifts', 'SYSTEM', 4),
    ('Groceries', 'SYSTEM', 4),
    ('Healthcare', 'SYSTEM', 4),
    ('Home', 'SYSTEM', 4),
    ('Other', 'SYSTEM', 4),
    ('Shopping', 'SYSTEM', 4),
    ('Sport and Hobbies', 'SYSTEM', 4),
    ('Transport', 'SYSTEM', 4),
    ('Travel', 'SYSTEM', 4);

-- Basic Wallet Transactions
INSERT INTO transactions(owner_id, wallet_id, type, category_id, payment, amount, note, date_created)
VALUES
    -- Basic Wallet Transactions
    -- Income transactions
    (1, 1, 'INCOME', 8, 'TRANSFER', 5000.00, 'Monthly salary', '2024-01-05'),
    (1, 1, 'INCOME', 1, 'TRANSFER', 1200.00, 'Business income', '2024-01-10'),
    (1, 1, 'INCOME', 2, 'TRANSFER', 300.00, 'Side job', '2024-01-12'),
    (1, 1, 'INCOME', 6, 'CASH', 450.00, 'Insurance claim', '2024-01-15'),
    (1, 1, 'INCOME', 4, 'CASH', 100.00, 'Gift from friend', '2024-01-18'),

    -- Expense transactions
    (1, 1, 'EXPENSE', 14, 'CARD', 200.00, 'Groceries', '2024-01-06'),
    (1, 1, 'EXPENSE', 16, 'CARD', 100.00, 'Gym membership', '2024-01-08'),
    (1, 1, 'EXPENSE', 17, 'CASH', 50.00, 'Public transportation', '2024-01-09'),
    (1, 1, 'EXPENSE', 11, 'CASH', 120.00, 'Clothing', '2024-01-11'),
    (1, 1, 'EXPENSE', 15, 'CARD', 30.00, 'Streaming subscription', '2024-01-13'),
    (1, 1, 'EXPENSE', 13, 'TRANSFER', 80.00, 'Family gift', '2024-01-14'),
    (1, 1, 'EXPENSE', 12, 'CARD', 90.00, 'Groceries', '2024-01-14'),
    (1, 1, 'EXPENSE', 10, 'CASH', 75.00, 'Health clinic visit', '2024-01-16'),
    (1, 1, 'EXPENSE', 9, 'CARD', 500.00, 'Rent payment', '2024-01-17'),
    (1, 1, 'EXPENSE', 18, 'TRANSFER', 300.00, 'Weekend trip', '2024-01-18'),
    (1, 1, 'EXPENSE', 8, 'CARD', 25.0, 'Movies', '2024-01-19'),
    (1, 1, 'EXPENSE', 7, 'CARD', 150.0, 'Gasoline', '2024-01-20'),
    (1, 1, 'EXPENSE', 11, 'TRANSFER', 100.0, 'Clothing store', '2024-01-21'),
    (1, 1, 'EXPENSE', 10, 'CARD', 200.0, 'Dental appointment', '2024-01-22'),
    (1, 1, 'EXPENSE', 9, 'TRANSFER', 300.0, 'Utility bill', '2024-01-23'),
    (1, 1, 'EXPENSE', 16, 'CASH', 60.0, 'Hiking supplies', '2024-01-24'),
    (1, 1, 'EXPENSE', 8, 'CARD', 45.0, 'Concert ticket', '2024-01-25'),
    (1, 1, 'EXPENSE', 17, 'CARD', 70.00, 'Taxi ride', '2024-01-26'),
    (1, 1, 'EXPENSE', 14, 'TRANSFER', 180.00, 'Monthly grocery trip', '2024-01-27'),

    -- Pro Wallet Transactions
    -- Income transactions
    (2, 2, 'INCOME', 8, 'TRANSFER', 7000.00, 'Monthly salary', '2024-01-05'),
    (2, 2, 'INCOME', 2, 'CASH', 1500.00, 'Freelance work', '2024-01-15'),
    (2, 2, 'INCOME', 6, 'TRANSFER', 500.00, 'Loan repayment', '2024-01-18'),
    (2, 2, 'INCOME', 1, 'CASH', 800.00, 'Business income', '2024-01-20'),
    (2, 2, 'INCOME', 4, 'TRANSFER', 300.00, 'Gift from family', '2024-01-22'),

    -- Expense transactions
    (2, 2, 'EXPENSE', 15, 'TRANSFER', 120.00, 'Streaming subscription', '2024-01-11'),
    (2, 2, 'EXPENSE', 9, 'CARD', 2000.00, 'Rent payment', '2024-01-01'),
    (2, 2, 'EXPENSE', 13, 'TRANSFER', 80.00, 'Birthday gift', '2024-01-02'),
    (2, 2, 'EXPENSE', 14, 'CARD', 150.00, 'Weekly groceries', '2024-01-06'),
    (2, 2, 'EXPENSE', 10, 'CARD', 220.00, 'Health check-up', '2024-01-08'),
    (2, 2, 'EXPENSE', 11, 'TRANSFER', 300.00, 'New clothing', '2024-01-09'),
    (2, 2, 'EXPENSE', 8, 'CARD', 30.00, 'Cinema ticket', '2024-01-12'),
    (2, 2, 'EXPENSE', 12, 'CASH', 80.00, 'Groceries', '2024-01-13'),
    (2, 2, 'EXPENSE', 18, 'CARD', 500.00, 'Vacation booking', '2024-01-14'),
    (2, 2, 'EXPENSE', 17, 'CASH', 15.00, 'Public transportation', '2024-01-16'),
    (2, 2, 'EXPENSE', 7, 'CARD', 200.00, 'Car repair', '2024-01-17'),
    (2, 2, 'EXPENSE', 15, 'TRANSFER', 25.00, 'Music streaming service', '2024-01-18'),
    (2, 2, 'EXPENSE', 10, 'CARD', 50.00, 'Health supplements', '2024-01-19'),
    (2, 2, 'EXPENSE', 13, 'CASH', 110.00, 'Gift shopping', '2024-01-21'),
    (2, 2, 'EXPENSE', 14, 'TRANSFER', 90.00, 'Weekly grocery trip', '2024-01-24'),

    -- Premium Wallet Transactions
    -- Income transactions
    (3, 3, 'INCOME', 8, 'TRANSFER', 10000.00, 'Monthly salary', '2024-01-05'),
    (3, 3, 'INCOME', 1, 'CASH', 2000.00, 'Business income', '2024-01-10'),
    (3, 3, 'INCOME', 2, 'TRANSFER', 500.00, 'Freelance work', '2024-01-12'),
    (3, 3, 'INCOME', 6, 'CASH', 800.00, 'Insurance claim', '2024-01-15'),
    (3, 3, 'INCOME', 4, 'TRANSFER', 300.00, 'Gift from friend', '2024-01-18'),

    -- Expense transactions
    (3, 3, 'EXPENSE', 14, 'CARD', 300.00, 'Groceries', '2024-01-06'),
    (3, 3, 'EXPENSE', 16, 'CARD', 200.00, 'Gym membership', '2024-01-08'),
    (3, 3, 'EXPENSE', 17, 'CASH', 100.00, 'Public transportation', '2024-01-09'),
    (3, 3, 'EXPENSE', 11, 'CASH', 150.00, 'Clothing', '2024-01-11'),
    (3, 3, 'EXPENSE', 15, 'CARD', 50.00, 'Streaming subscription', '2024-01-13'),
    (3, 3, 'EXPENSE', 13, 'TRANSFER', 80.00, 'Family gift', '2024-01-14'),
    (3, 3, 'EXPENSE', 12, 'CARD', 90.00, 'Groceries', '2024-01-14'),
    (3, 3, 'EXPENSE', 10, 'CASH', 75.00, 'Health clinic visit', '2024-01-16'),
    (3, 3, 'EXPENSE', 9, 'CARD', 500.00, 'Rent payment', '2024-01-17'),
    (3, 3, 'EXPENSE', 18, 'TRANSFER', 300.00, 'Weekend trip', '2024-01-18'),
    (3, 3, 'EXPENSE', 8, 'CARD', 25.00, 'Movies', '2024-01-19'),
    (3, 3, 'EXPENSE', 7, 'CARD', 150.00, 'Gasoline', '2024-01-20'),
    (3, 3, 'EXPENSE', 11, 'TRANSFER', 100.00, 'Clothing store', '2024-01-21'),
    (3, 3, 'EXPENSE', 10, 'CARD', 200.00, 'Dental appointment', '2024-01-22'),
    (3, 3, 'EXPENSE', 9, 'TRANSFER', 300.00, 'Utility bill', '2024-01-23'),

    -- Admin Wallet Transactions
    -- Income transactions
    (4, 4, 'INCOME', 8, 'TRANSFER', 15000.00, 'Monthly salary', '2024-01-05'),
    (4, 4, 'INCOME', 1, 'CASH', 3000.00, 'Business income', '2024-01-10'),
    (4, 4, 'INCOME', 2, 'TRANSFER', 750.00, 'Freelance work', '2024-01-12'),
    (4, 4, 'INCOME', 6, 'CASH', 1200.00, 'Insurance claim', '2024-01-15'),
    (4, 4, 'INCOME', 4, 'TRANSFER', 450.00, 'Gift from friend', '2024-01-18'),

    -- Expense transactions
    (4, 4, 'EXPENSE', 14, 'CARD', 400.00, 'Groceries', '2024-01-06'),
    (4, 4, 'EXPENSE', 16, 'CARD', 300.00, 'Gym membership', '2024-01-08'),
    (4, 4, 'EXPENSE', 17, 'CASH', 150.00, 'Public transportation', '2024-01-09'),
    (4, 4, 'EXPENSE', 11, 'CASH', 200.00, 'Clothing', '2024-01-11'),
    (4, 4, 'EXPENSE', 15, 'CARD', 75.00, 'Streaming subscription', '2024-01-13'),
    (4, 4, 'EXPENSE', 13, 'TRANSFER', 120.00, 'Family gift', '2024-01-14'),
    (4, 4, 'EXPENSE', 12, 'CARD', 135.00, 'Groceries', '2024-01-14'),
    (4, 4, 'EXPENSE', 10, 'CASH', 100.00, 'Health clinic visit', '2024-01-16'),
    (4, 4, 'EXPENSE', 9, 'CARD', 750.00, 'Rent payment', '2024-01-17'),
    (4, 4, 'EXPENSE', 18, 'TRANSFER', 450.00, 'Weekend trip', '2024-01-18'),
    (4, 4, 'EXPENSE', 8, 'CARD', 50.00, 'Movies', '2024-01-19'),
    (4, 4, 'EXPENSE', 7, 'CARD', 300.00, 'Gasoline', '2024-01-20'),
    (4, 4, 'EXPENSE', 11, 'TRANSFER', 150.00, 'Clothing store', '2024-01-21'),
    (4, 4, 'EXPENSE', 10, 'CARD', 300.00, 'Dental appointment', '2024-01-22'),
    (4, 4, 'EXPENSE', 9, 'TRANSFER', 450.00, 'Utility bill', '2024-01-23');
