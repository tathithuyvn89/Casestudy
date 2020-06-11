create database casestudy_module3;

use  casestudy_module3;

create table users
(
    id       int auto_increment primary key,
    fullname varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(255) unique ,
    role     int default 0

);
create table products(
                         id int auto_increment primary key,
                         name varchar(255) not null ,
                         price double not null ,
                         description varchar(255) not null ,
                         img varchar(500) not null ,
                         maker varchar(100)
);
drop table products;

create table oders (
                       orderId  int auto_increment primary key,
                       user_id int ,
                       product_id int,
                       status int not null,
                       date DATE not null,
                       foreign key (user_id) references users(id),
                       foreign key (product_id) references products(id)
);




