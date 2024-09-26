-- Создание таблицы customers
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

-- Вставка данных в таблицу customers
INSERT INTO customers (first_name, last_name, age, country)
VALUES
    ('Иван', 'Иванов', 25, 'Россия'),
    ('Петр', 'Петров', 30, 'Россия'),
    ('Светлана', 'Сидорова', 22, 'Россия'),
    ('Алексей', 'Алексеев', 30, 'Россия'),
    ('Мария', 'Маркова', 28, 'Россия');

-- Запрос для получения списка клиентов с минимальным возрастом
SELECT * FROM customers WHERE age = (SELECT MIN(age) FROM customers);

-- Создание таблицы orders
CREATE TABLE orders1
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

-- Вставка данных в таблицу orders
INSERT INTO orders1 (amount, customer_id) VALUES
                                             (100, 1),
                                             (200, 2),
                                             (150, 1),
                                             (300, 4);

-- Запрос для получения списка пользователей, которые еще не выполнили ни одного заказа
SELECT * FROM customers WHERE id NOT IN (SELECT customer_id FROM orders1);