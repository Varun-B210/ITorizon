	insert into category (category_Desc)
	values ("Hardware"),("Software"),("Access Management");
	COMMIT;
	
	insert into sub_category (category_Id, sub_Category_Desc)
	values 	(1, "Allocate Laptop"),
			(1, "Allocate Hardware"),
			(1, "Hardware replacement"),
			(2, "Software Installation"),
			(2, "Antivirus"),
			(2, "Email Password update"),
			(2, "Laptop Slowness issue"),
			(2, "Software Issue"),
			(3,	"Software access"),
			(3, "Wifi Access"),
			(3, "Database Access"),
			(3, "VPN Access");
	COMMIT;
	
	insert into admin_team (name, email_Id)
	values 	("varun", "varun@yahoo.com"),
			("dhanush", "dhanush@gmail.com"),
			("shreyas", "shreyas@gmail.com");
	COMMIT;
	
	insert into user (name, email_Id, create_Date_time, last_Modified_Date_Time)
	values	("karthik", "karthik@gmail.com", now(), now()),
			("akshay", "akshay@hotmail.com", now(), now()),
			("pavan", "pavan@yahoo.com", now(), now());
	COMMIT;
	
	insert into status (status) values ("Open"),("Assigned"), ("InProgress"), ("Completed");
	COMMIT;
	
	insert into priority (priority) values ("Low"), ("Medium"), ("High"), ("Critical");
	COMMIT;
	
	