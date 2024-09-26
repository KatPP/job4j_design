CREATE TABLE products_test(id serial PRIMARY KEY, name varchar(50), producer varchar(50), count integer DEFAULT 0, price integer);

INSERT INTO products_test (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products_test (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
INSERT INTO products_test (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

BEGIN TRANSACTION;

-- Вставка новой записи
INSERT INTO products_test (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

-- Установка первой точки сохранения
SAVEPOINT first_savepoint;

-- Вставка еще одной записи
INSERT INTO products_test (name, producer, count, price) VALUES ('product_5', 'producer_5', 5, 75);

-- Установка второй точки сохранения
SAVEPOINT second_savepoint;

-- Обновление записи
UPDATE products_test SET price = 80 WHERE name = 'product_1';

-- Установка третьей точки сохранения
SAVEPOINT third_savepoint;

-- Удаление записи
DELETE FROM products_test WHERE name = 'product_3';

-- Проверка состояния таблицы
SELECT * FROM products_test;

-- Откат к первой точке сохранения
ROLLBACK TO SAVEPOINT first_savepoint;

-- Проверка состояния таблицы после отката
SELECT * FROM products_test;

-- Откат к третьей точке сохранения
ROLLBACK TO SAVEPOINT third_savepoint;

-- Проверка состояния таблицы после отката
SELECT * FROM products_test;

-- Завершение транзакции
COMMIT;