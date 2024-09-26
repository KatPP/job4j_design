-- Создание таблицы movie
CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

-- Создание таблицы book
CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

-- Вставка данных в таблицу movie
INSERT INTO movie (name, director)
VALUES
    ('Марсианин', 'Ридли Скотт'),
    ('Матрица', 'Братья Вачовски'),
    ('Властелин колец', 'Питер Джексон'),
    ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
    ('Железный человек', 'Джон Фавро');

-- Вставка данных в таблицу book
INSERT INTO book (title, author)
VALUES
    ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
    ('Властелин колец', 'Джон Толкин'),
    ('1984', 'Джордж Оруэлл'),
    ('Марсианин', 'Энди Уир'),
    ('Божественная комедия', 'Данте Алигьери');

-- Запрос 1: Вывод названий всех фильмов, которые сняты по книге
SELECT m.name FROM movie m JOIN book b ON m.name = b.title;

-- Запрос 2: Вывод всех названий книг, у которых нет экранизации
SELECT b.title FROM book b LEFT JOIN movie m ON b.title = m.name
WHERE m.name IS NULL;

-- Запрос 3: Вывод всех уникальных названий произведений из таблиц movie и book
SELECT name AS title FROM movie WHERE name NOT IN (SELECT title FROM book)
UNION
SELECT title FROM book
WHERE title NOT IN (SELECT name FROM movie);