
drop table if exists category;
drop table if exists sub_category;
drop table if exists admin_team;
drop table if exists user;
drop table if exists status;
drop table if exists priority;

create table category
(
   category_Id int primary key auto_increment,
   category_Desc varchar (30)
);

create table sub_category
(
   sub_Category_Id int primary key auto_increment,
   category_Id int,
   sub_Category_Desc varchar (50)
);

create table admin_team
(
   admin_Id int primary key auto_increment,
   name varchar (30),
   email_Id varchar (100)
   
);

create table user
(
	user_Id int primary key auto_increment,
   name varchar (30),
  email_Id varchar (100),
   create_Date_Time datetime,
   last_Modified_Date_Time datetime
);

create table status
(
   status_Id int primary key auto_increment,
   status varchar (30)
   
);

create table priority
(
   priority_Id int primary key auto_increment,
   priority varchar (30)
);




