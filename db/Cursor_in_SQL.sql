CREATE TABLE products1 (id    SERIAL PRIMARY KEY, name  VARCHAR(50), count INTEGER DEFAULT 0, price INTEGER);

INSERT INTO products1 (name, count, price) VALUES ('product_1', 1, 5);
INSERT INTO products1 (name, count, price) VALUES ('product_2', 2, 10);
INSERT INTO products1 (name, count, price) VALUES ('product_3', 3, 15);
INSERT INTO products1 (name, count, price) VALUES ('product_4', 4, 20);
INSERT INTO products1 (name, count, price) VALUES ('product_5', 5, 25);
INSERT INTO products1 (name, count, price) VALUES ('product_6', 6, 30);
INSERT INTO products1 (name, count, price) VALUES ('product_7', 7, 35);
INSERT INTO products1 (name, count, price) VALUES ('product_8', 8, 40);
INSERT INTO products1 (name, count, price) VALUES ('product_9', 9, 45);
INSERT INTO products1 (name, count, price) VALUES ('product_10', 10, 50);
INSERT INTO products1 (name, count, price) VALUES ('product_11', 11, 55);
INSERT INTO products1 (name, count, price) VALUES ('product_12', 12, 60);
INSERT INTO products1 (name, count, price) VALUES ('product_13', 13, 65);
INSERT INTO products1 (name, count, price) VALUES ('product_14', 14, 70);
INSERT INTO products1 (name, count, price) VALUES ('product_15', 15, 75);
INSERT INTO products1 (name, count, price) VALUES ('product_16', 16, 80);
INSERT INTO products1 (name, count, price) VALUES ('product_17', 17, 85);
INSERT INTO products1 (name, count, price) VALUES ('product_18', 18, 90);
INSERT INTO products1 (name, count, price) VALUES ('product_19', 19, 95);
INSERT INTO products1 (name, count, price) VALUES ('product_20', 20, 100);

BEGIN;

DECLARE cursor_products CURSOR FOR SELECT * FROM products1;

-- Перемещение на последнюю запись
FETCH LAST FROM cursor_products;

-- Чтение последней записи
FETCH FROM cursor_products;

-- Перемещение на 15-ю запись
MOVE BACKWARD 5 FROM cursor_products;
FETCH FROM cursor_products;

-- Перемещение на 7-ю запись
MOVE BACKWARD 8 FROM cursor_products;
FETCH FROM cursor_products;

-- Перемещение на 2-ю запись
MOVE BACKWARD 5 FROM cursor_products;
FETCH FROM cursor_products;

-- Перемещение на 1-ю запись
MOVE BACKWARD 1 FROM cursor_products;
FETCH FROM cursor_products;

-- Завершение транзакции
COMMIT;