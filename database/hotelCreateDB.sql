create database casestudy_module3;

use  casestudy_module3;

create table users(
                      id int auto_increment primary key ,
                      fullname varchar(255) not null ,
                      username varchar(255) not null unique ,
                      password varchar(255) not null,
                      role int default 0
);



