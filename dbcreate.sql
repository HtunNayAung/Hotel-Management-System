use hoteldb;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

create table login(username varchar(30), 
					password varchar(30));
insert into login values('admin', 'admin123');
insert into login values('staff', 'staff123');

create table department(name varchar(50) NOT NULL, 
						budget decimal(10,2) NOT NULL,
                        constraint departmentPK primary key(name));

create table employee(id integer not null auto_increment,
					name varchar(50) NOT NULL, 
					dob date NOT NULL, 
                    gender varchar(20) NOT NULL, 
                    job varchar(30) NOT NULL, 
                    salary decimal(10,2) NOT NULL, 
                    phone varchar(10) NOT NULL, 
                    email varchar(30) NOT NULL,
                    department varchar(50) NOT NULL,
                    constraint employeePK primary key (id),
                    constraint employeeFK foreign key (department) references department(name)) auto_increment = 1001 ;

create table room(roomNumber varchar(10) NOT NULL, 
				  availability varchar(15) NOT NULL, 
                  cleaningStatus varchar(20) NOT NULL, 
                  price decimal(6,2) NOT NULL, 
                  roomType varchar(15) NOT NULL, 
                  constraint roomPK primary key (roomNumber));

create table customer(name varchar(50) NOT NULL, 
					  idType varchar(20) NOT NULL, 
                      idNumber varchar(20) NOT NULL, 
                      gender varchar(6) NOT NULL, 
                      country varchar(30) NOT NULL, 
                      roomNumber varchar(5) NOT NULL, 
                      checkinTime DateTime NOT NULL,
                      constraint customerPK primary key (name, idNumber), 
                      constraint customerFK foreign key (roomNumber) references room (roomNumber));
                      
create table staying (name varchar(50) NOT NULL,
					  roomNumber varchar(10) NOT NULL,
                      constraint stayingPK primary key (name, roomNumber),
                      constraint stayingFK1 foreign key (name) references customer (name),
                      constraint stayingFK2 foreign key (roomNumber) references room (roomNumber));


insert into department values ("Front Office", 500000), 
								("Finance", 700000), 
                                ("F&B", 1200000), 
                                ("Marketing", 600000), 
                                ("Maintenance", 800000), 
                                ("Security", 600000), 
                                ("Food Production", 10000000);

update room set availability = "Available" where roomNumber = "101";
select * from room;
select * from staying;
drop table customer;
drop table staying;
select * from department;
select * from employee;
select * from customer;

delete from customer where roomNumber = '101';





