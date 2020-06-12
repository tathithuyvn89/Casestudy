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
                       quantity int,
                       price double,
                       status int not null,
                       date varchar(50) not null,
                       foreign key (user_id) references users(id),
                       foreign key (product_id) references products(id)
);
create
    definer = root@localhost procedure addOrder(IN userId int)
begin
    insert into orders(orderUserId, orderDate) values (userId,curdate());
end;

create table orderDetails(
                             user_id int,
                             product_id int,
                             quantity int,
                             price double,
                             primary key (user_id,product_id),
                             foreign key (user_id) references users(id),
                             foreign key (product_id) references products(id)
);
create
    definer = root@localhost procedure addOrder(IN userId int)
begin
    insert into orders(orderUserId, orderDate) values (userId,curdate());
end;

DELIMITER //
CREATE PROCEDURE insertOrderDetail(
    user_id int,
    product_id int,
    quantity int,
    price double
)
begin
    insert into orderdetails(user_id, product_id, quantity, price) values (user_id,product_id,quantity,price);
end //
delimiter ;

