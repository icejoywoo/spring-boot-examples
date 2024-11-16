create database test character set utf8mb4 collate utf8mb4_general_ci;

use test;

create table student (
    id int auto_increment primary key,
    name varchar(50) not null,
    email varchar(50) not null,
    age int
);

insert into student(name, email, age) values("eric", "abc@123.com", 20);

curl -X POST http://localhost:8080/student/ \
   -H 'Content-Type: application/json' \
   -d '{"name":"eric","email":"abc@123.com"}'