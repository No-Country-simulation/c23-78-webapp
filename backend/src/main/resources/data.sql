
insert into `action`(action_name) values
("LOGIN"),("LOGOUT"),("CREATE_ORDER"),("UPDATE_ORDER"),("DELETE_ORDER");

insert into user(discriminator, name, last_name, phone, dni, email, address, password, active, role) values
('Admin','Raylene','Coleman','-26894333411','76415901','nemanjaivanovic@test.trade','North Dakota 521','$2a$10$qZbZgi7CQjPxRJvfQfi.LecIniFbuM/54KL9F8CySHeitaJ9UbCJy',1,'ADMIN'),
('Admin','Hai','Cummings','6886865200415','80998288','marcobarbosa@test.homegoods','Montana 770','$2a$10$.9uCGgiEuUL2Hdv8vo3BteSNrv/hJI3DTym7z0m3jPyFjnk8xhFdi',1,'ADMIN'),
('Admin','Hassan','Page','-34537986175','73466300','soyjavi@example.codes','North Carolina 643','$2a$10$uPOlWlJGdmro9NTRPVNk0unzVqG1jE37WF9UCRE.QVS5JSym12Goa',1,'ADMIN'),
('Admin','Manual','Logan','908422930690','73084716','nandini_m@example.xn--mgbb9fbpob','Massachusetts 136','$2a$10$WaiPYW2UTvX9h2lG.5W18.BuauW/QJ/rwYY2GkBlnQJW9NWfzr1W2',1,'ADMIN'),
('Technician','Francoise','Robinson','9951367577407','44809493','sgaurav_baghel@example.ubs','Pennsylvania 864','$2a$10$Ucx13pk7h0a6VNpGD0FJI.2TYR..ek4r.jUeLUqGP7zIDVSAZ14em',1,'TECHNICIAN'),
('Technician','Sandie','Pierce','5088543501975','73561628','mutlu82@example.jo','Florida 77','$2a$10$rNdMpzgiD7zJfF5d1S6WKORUVXZ21Bs9sNNJwhNmpfmPO6YYyW4ZW',1,'TECHNICIAN'),
('Technician','Danuta','Wagner','3573903361278','32771239','leemunroe@test.onyourside','Indiana 983','$2a$10$Tlv48P9oWQvQ5ACQEtt1zOfHG7AwpMDMhKmtoiZfozTPXCsbE0TCi',1,'TECHNICIAN'),
('Technician','Hong','Ellis','559222718892','73236667','syropian@test.cooking','New York 834','$2a$10$P4usHMnaFumCjh9ROtwu3uLFl0.rmg3PGss41aNUB3fHTpcBXCo9e',1,'TECHNICIAN'),
('Client','Porter','Jacobs','632348530864','30778676','jeremiaha@test.buzz','Ohio 535','$2a$10$jHQNvjl1RK2jZ34JHQ/WiuYkUFlvZiustaWvxebxiItY2dUob.wUy',1,'CLIENT'),
('Client','Olen','Chapman','501202873710','58754351','soyjavi@test.td','Utah 667','$2a$10$6j7FmHHr.bQ8c1jAMhaygeE4p2QNcqAFcLMAQkDBXSvdKxSNuTWIq',1,'CLIENT'),
('Client','Brenton','Doyle','9648491165459','49615138','anoff@example.nikon','Missouri 78','$2a$10$3KdciiXvHBKqP9HpQHq4RuG735GqXP3Mo.qqtlmp06.rHilIFOY6C',1,'CLIENT'),
('Client','Hai','Nunez','2261619234495','29783685','madysondesigns@test.site','Nevada 878','$2a$10$5Dg3GLQYOhwuMod/ZGlvO.ooZPxjbM9MoKp/xG/PHg3zGbDD0T9Dq',1,'CLIENT'),
('Client','Columbus','George','3735254485422','23424060','beweinreich@test.lpl','Missouri 63','$2a$10$kbA9xVp.R1NNxXWGuwXQZOuWaOU.nOO3RZiAzge4o5lARH8VrhR5i',1,'CLIENT'),
('Client','Arminda','Evans','2421478367900','48918760','tgerken@test.globo','Maryland 730','$2a$10$o6Yl.biDVqHmeAEEQMhUreWz0gbc/iwgaHhcaCVWeLBvDd7BgSBf2',1,'CLIENT'),
('Client','Jamey','Knight','9606310246291','11291010','krasnoukhov@test.dentist','California 380','$2a$10$JoS6GshSZuOSLUqeQRv3B.wi2n3f8yRh4Dvxy3HWanmcCOgN1YI4.',0,'CLIENT');



insert into `order`(number, observations, initial_price, final_price, id_client) values
("ORD001", "Order 1", 100.0, 150.0, 1 ),
("ORD002", "Order 2", 200.0, 250.0, 2 ),
("ORD003", "Order 3", 300.0, 350.0, 3 ),
("ORD004", "Order 4", 400.0, 450.0, 4 ),
("ORD005", "Order 5", 500.0, 550.0, 5 ),
("ORD006", "Order 6", 600.0, 650.0, 6 ),
("ORD007", "Order 7", 700.0, 750.0, 7 ),
("ORD008", "Order 8", 800.0, 850.0, 8 ),
("ORD009", "Order 9", 900.0, 950.0, 9 ),
("ORD010", "Order 10", 1000.0, 1050.0, 10 );

insert into `state`(state_name) values
("NEW"),
("REPAIRED"),
("DISPATCHED"),
("DELIVERED"),
("CANDELLED");

insert into type(type_name) values
("Laptop"),
("Phone"),
("Tablet"),
("Desktop"),
("Wearable");

insert into device(model, serial_number, accessories, description, id_order, id_state, id_type) values
("Model A", "SN12345", "Charger", "New device",1,1,1 ),
("Model B", "SN54321", "Cover", "Used device",2,2,2 ),
("Model C", "SN67890", "Keyboard", "Refurbished device",3,3,3 ),
("Model D", "SN09876", "Mouse", "Old device",4,4,4 ),
("Model E", "SN11223", "Stand", "Damaged device",5,5,5 ),
("Model F", "SN44556", "Headphones", "New device",6,1,1 ),
("Model G", "SN77889", "Cable", "Used device",7,2,2 ),
("Model H", "SN99000", "Adapter", "Refurbished device",8,3,3 ),
("Model I", "SN11334", "Battery", "Old device",9,4,4 ),
("Model J", "SN55667", "Screen Protector", "Damaged device",10,5,5 );

("CREATE"),("DELETE"),("EXPORT"),("READ"),("UPDATE");

insert into user(discriminator, name, last_name, phone, dni, email, address, password, active, role) values
('Admin','Raylene','Coleman','-26894333411','76415901','coleman@test.com','North Dakota 521','$2a$10$qZbZgi7CQjPxRJvfQfi.LecIniFbuM/54KL9F8CySHeitaJ9UbCJy',1,'ADMIN'),
('Technician','Francoise','Robinson','9951367577407','44809493','franco@example.com','Pennsylvania 864','$2a$10$Ucx13pk7h0a6VNpGD0FJI.2TYR..ek4r.jUeLUqGP7zIDVSAZ14em',1,'TECHNICIAN'),
('Technician','Sandie','Pierce','5088543501975','73561628','sandy@example.com','Florida 77','$2a$10$rNdMpzgiD7zJfF5d1S6WKORUVXZ21Bs9sNNJwhNmpfmPO6YYyW4ZW',1,'TECHNICIAN'),
('Technician','Danuta','Wagner','3573903361278','32771239','wagner@test.com','Indiana 983','$2a$10$Tlv48P9oWQvQ5ACQEtt1zOfHG7AwpMDMhKmtoiZfozTPXCsbE0TCi',1,'TECHNICIAN'),
('Client','Porter','Jacobs','632348530864','30778676','jeremiaha@test.buzz','Ohio 535',NULL,1,'CLIENT'),
('Client','Olen','Chapman','501202873710','58754351','soyjavi@test.td','Utah 667',NULL,1,'CLIENT'),
('Client','Brenton','Doyle','9648491165459','49615138','anoff@example.nikon','Missouri 78',NULL,1,'CLIENT'),
('Client','Hai','Nunez','2261619234495','29783685','madysondesigns@test.site','Nevada 878',NULL,1,'CLIENT'),
('Client','Columbus','George','3735254485422','23424060','beweinreich@test.lpl','Missouri 63',NULL,1,'CLIENT'),
('Client','Arminda','Evans','2421478367900','48918760','tgerken@test.globo','Maryland 730',NULL,1,'CLIENT'),
('Client','Jhon','Doe','2421478367910','48918761','jhondoe@test.globo','kentucky 710',NULL,1,'CLIENT'),
('Client','Hai','Cummings','6886865200415','80998288','marcobarbosa@test.homegoods','Montana 770',NULL,1, 'CLIENT'),
('Client','Hassan','Page','-34537986175','73466300','soyjavi@example.codes','North Carolina 643',NULL,1, 'CLIENT'),
('Client','Manual','Logan','908422930690','73084716','nandini_m@example.xn--mgbb9fbpob','Massachusetts 136',NULL,1, 'CLIENT'),
('Client','Jamey','Knight','9606310246291','11291010','krasnoukhov@test.dentist','California 380',NULL,0,'CLIENT');



INSERT INTO `order`(final_price,initial_price,id_client,number,observations) VALUES
	 (1000.00,.0,5,'ORD-0001','Observation1'),
	 (.0,2000.00,6,'ORD-0002','Observation2'),
	 (1500.00,500.00,7,'ORD-0003','Observation3'),
	 (2000.00,800.00,8,'ORD-0004','Observation4'),
	 (2500.00,1200.00,9,'ORD-0005','Observation5'),
	 (1700.00,300.00,10,'ORD-0006','Observation6'),
	 (2200.00,600.00,11,'ORD-0007','Observation7'),
	 (2700.00,900.00,12,'ORD-0008','Observation8'),
	 (1900.00,400.00,5,'ORD-0009','Observation9'),
	 (3000.00,1100.00,6,'ORD-0010','Observation10');


insert into `state`(state_name) values
("RECEIVED"),
("REPAIRED"),
("DISPATCHED"),
("DELIVERED"),
("CANDELLED");

insert into type(type_name) values
("MONITOR"),
("TV"),
("CPU"),
("PLACA DE VIDEO"),
("TABLET");

INSERT INTO `device` (id_order,id_state,id_type,model,serial_number,accessories,description) VALUES
	 (1,5,3,'Model1','SN-00001','Accessories1','Description1'),
	 (2,4,1,'Model2','SN-00002','Accessories2','Description2'),
	 (3,3,4,'Model3','SN-00003','Accessories3','Description3'),
	 (4,1,5,'Model4','SN-00004','Accessories4','Description4'),
	 (5,2,2,'Model5','SN-00005','Accessories5','Description5'),
	 (6,5,3,'Model6','SN-00006','Accessories6','Description6'),
	 (7,4,1,'Model7','SN-00007','Accessories7','Description7'),
	 (8,3,4,'Model8','SN-00008','Accessories8','Description8'),
	 (9,1,5,'Model9','SN-00009','Accessories9','Description9'),
	 (10,2,2,'Model10','SN-00010','Accessories10','Description10');
	 (1,5,3,'Model11','SN-00011','Accessories11','Description11'),
	 (2,4,1,'Model12','SN-00012','Accessories12','Description12'),
	 (3,3,4,'Model13','SN-00013','Accessories13','Description13'),
	 (4,1,5,'Model14','SN-00014','Accessories14','Description14'),
	 (5,2,2,'Model15','SN-00015','Accessories15','Description15');



insert into action_user(name) values
("CREATE"),
("READ"),
("UPDATE"),
("DELETE"),
("EXPORT");

INSERT into user_changes(id_action_user,id_technician) values
(1,1 ),
(2,2 ),
(3,3 ),
(4,4 ),
(5,5 ),
(1,6 ),
(2,7 ),
(3,8 ),
(4,9 ),
(5,10 );