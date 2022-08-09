create table person(
id int not null primary key,
name varchar(30) not null,
location varchar(30),
birthday timestamp);

insert into person (id, name, location, birthday)
values (1001, 'Varun', 'Bangalore','1996-10-02'),
(1002, 'Dhanush', 'Bangalore','1996-10-18'),
(1003, 'Karthik', 'Mangalore','1996-07-02'),
(1004, 'Danish', 'Mangalore','1996-02-21');

