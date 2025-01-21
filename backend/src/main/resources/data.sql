
insert into `action`(action_name) values
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