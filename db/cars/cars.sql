create table cars(
	id serial primary key,
	name varchar(50),
	speed int,
	price float,
	has_owner boolean,
	country varchar(50)
);
insert into cars(name, speed, price, has_owner, country)
values('Volvo', 210, 28999.00, true, 'Sweden');
update cars set name = 'Lada', speed = 150, price = 12300.50, has_owner = false, country = 'Russia';
delete from cars;