# TIMS

Java application which maintains three-wheeler information in Hatton-Dickoya area. 

## What is TIMS?
* Three-Wheelar Information Management System (TIMS) manages Three-Wheelars' information in the Hatton, and Dickoya area. 
* I have tried to implement this project based on Object-Oriented Programming Paradigm. 
* This system is currently used by Hatton-Dickoya Urban Council. 

## Technologies
Project is created with:
* Java 8
* MySQL

## Getting Started
* Fork and clone the repository:
```
$ https://github.com/USERNAME/tims.git
```

* Import tims into your IDE

* Setup MySQL database by executing queries in `tims/MySQL-Scripts/scripts.sql`

* Make sure that you specify correct MySQL properties in `tims/src/com/uc/tims/mysql/MySQLConnection.java`:
    1. Database url in the form jdbc:subprotocol:subname
    2. Database user name who tries to establish the connection
    3. Database user's password
    
    Here is the [documentation](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.lang.String-java.lang.String-) on DriverManager to get MySQL connection.
    
* Please note that this project is done using Java `1.8.0_212` and have not been tested for support in other versions.
