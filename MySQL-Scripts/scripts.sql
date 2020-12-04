# Creating database tims
CREATE DATABASE tims;

# DROP DATABASE tims;

# use the tims database
USE tims;

# create table role
CREATE TABLE role (
	id INT NOT NULL,
    rolename VARCHAR(20),
    
    PRIMARY KEY (id)
);

# create table employee
CREATE TABLE employee (
	id INT NOT NULL AUTO_INCREMENT,
    roleid INT,
    name VARCHAR(255),
    username VARCHAR(255),
    nic VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    job VARCHAR(255),
    password VARCHAR(255),
    
    PRIMARY KEY (id),
    FOREIGN KEY (roleid) REFERENCES role(id)
);

# create table driver
CREATE TABLE driver (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	nic VARCHAR(255) NOT NULL,
    phoneno INT,
    wheelno VARCHAR(255),
    address VARCHAR(255),
    parkno VARCHAR(255),
	park VARCHAR(10),
    # BLOB datatype was not enough for images. So, changing it to MEDIUMBLOB
    # TINYBLOB ≈ 255 bytes, BLOB ≈ 64KB, MEDIUMBLOB ≈ 16MB and LONGBLOB ≈ 4GB
    images MEDIUMBLOB,
    imageurl VARCHAR(255),
    gs VARCHAR(10),
    
    PRIMARY KEY (id)
);

# create table payment
CREATE TABLE payment (
	id INT NOT NULL,
	name VARCHAR(255),
	nic VARCHAR(255) NOT NULL,
    park VARCHAR(255),
    # decimal is an exact fixed-point number
    # total number of digits = 15, number of digits after decimal point = 2
    totalpayment DECIMAL(15,2),
    year2013 DECIMAL(15,2),
    year2014 DECIMAL(15,2),
    year2015 DECIMAL(15,2),
    year2016 DECIMAL(15,2),
    year2017 DECIMAL(15,2),
    year2018 DECIMAL(15,2),
    year2019 DECIMAL(15,2),
    year2020 DECIMAL(15,2),
    year2021 DECIMAL(15,2),
    year2022 DECIMAL(15,2),
    
    PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES driver(id)
);


# create table employee_driver
CREATE TABLE employee_driver (
	employeeid INT NOT NULL,
    driverid INT NOT NULL,
    
    PRIMARY KEY (employeeid, driverid),
    FOREIGN KEY (employeeid) REFERENCES employee(id),
    FOREIGN KEY (driverid) REFERENCES driver(id)
);

# insert details into role table TO BE EXECUTED!!
INSERT INTO role VALUES
(1, "admin"),
(2, "user");

SELECT * FROM role; 

# insert admin details into the system
INSERT INTO employee(roleid, name, username, nic, email, job, password) VALUES
(1,"Urban Council","Hatton-UC","971245637V","mvthanoshan@gmail.com","Assistant","2019");

# select all rows
SELECT * FROM employee;

SELECT * FROM driver;

SELECT * FROM payment;

SELECT * FROM role;

# delete all rows from payment table
# DELETE FROM payment;

# delete all rows from driver table
# DELETE FROM driver;

# we can set auto increment from any number
# ALTER TABLE driver AUTO_INCREMENT = 1;

# show all tables in tims database
SHOW TABLES FROM tims;

# show role table's columns 
SHOW COLUMNS FROM role;

# constraining rolename to be not null
ALTER TABLE role
MODIFY COLUMN rolename VARCHAR(20) NOT NULL;

# show role table's columns 
SHOW COLUMNS FROM employee;

# constraining username to be not null
ALTER TABLE employee
MODIFY COLUMN username VARCHAR(255) NOT NULL;

# changing phoneno in driver's table from INT to VARCHAR for the purpose of simplicity
ALTER TABLE driver
MODIFY COLUMN phoneno VARCHAR(255);

# show driver table's columns 
SHOW COLUMNS FROM driver;

# constrainting wheelno, parkno to be not null in the driver's table
ALTER TABLE driver
MODIFY COLUMN wheelno VARCHAR(255) NOT NULL;

ALTER TABLE driver
MODIFY COLUMN parkno VARCHAR(255) NOT NULL;




