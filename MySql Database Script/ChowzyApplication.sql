create database application;
use application;

create table userCredentials(
	userId int primary key auto_increment,
    userName varchar(100) not null,
    address varchar(150) not null,
    phone bigint not null,
	userEmail varchar(50) not null ,
    userPassword varchar(50) not null
)auto_increment=10;


create table restaurant(
	resId int primary key,
    resEmail varchar(50) unique not null,
    password varchar(50) not null,
	resName varchar(100) not null,
    resArea varchar(60) not null,
    phone bigint not null
);


create table food(
	foodId int primary key auto_increment,
    food varchar(75) not null,
    resId int,
    price float,
    foreign key (resId) references restaurant(resId)
	ON DELETE CASCADE 
    ON UPDATE CASCADE,
  UNIQUE (foodId,food,resId)
)auto_increment=0;

CREATE TABLE cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    food_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (food_id) REFERENCES food(foodId)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);



CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    res_id INT not null,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT current_timestamp,
    order_status varchar(50) default 'pending',
    foreign key (res_id) references restaurant(resId)
    on delete cascade
    on update cascade
);



CREATE TABLE order_details (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    res_id INT not null,
    food_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    foreign key (res_id) references restaurant(resId)
    on delete cascade
    on update cascade,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (food_id) REFERENCES food(foodId)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);
