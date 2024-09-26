
-- Таблица арендаторов
CREATE TABLE tenants (
                         tenant_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50),
                         address VARCHAR(255),
                         gender VARCHAR(10)
);

-- Таблица фильмов
CREATE TABLE movies (
                        movie_id SERIAL PRIMARY KEY,
                        title VARCHAR(100)
);

-- Таблица аренд
CREATE TABLE rentals1 (
                         rental_id SERIAL PRIMARY KEY,
                         tenant_id INT REFERENCES tenants(tenant_id),
                         movie_id INT REFERENCES movies(movie_id),
                         rental_date DATE
);

