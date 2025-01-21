
--insert into `action`(action_name) values
--("LOGIN"),("LOGOUT"),("CREATE_ORDER"),("UPDATE_ORDER"),("DELETE_ORDER");

--insert into user(discriminator, name, last_name, phone, dni, email, address, password, active, role) values
--('Admin','Raylene','Coleman','-26894333411','76415901','nemanjaivanovic@test.trade','North Dakota 521','$2a$10$qZbZgi7CQjPxRJvfQfi.LecIniFbuM/54KL9F8CySHeitaJ9UbCJy',1,'ADMIN'),
--('Admin','Hai','Cummings','6886865200415','80998288','marcobarbosa@test.homegoods','Montana 770','$2a$10$.9uCGgiEuUL2Hdv8vo3BteSNrv/hJI3DTym7z0m3jPyFjnk8xhFdi',1,'ADMIN'),
--('Admin','Hassan','Page','-34537986175','73466300','soyjavi@example.codes','North Carolina 643','$2a$10$uPOlWlJGdmro9NTRPVNk0unzVqG1jE37WF9UCRE.QVS5JSym12Goa',1,'ADMIN'),
--('Admin','Manual','Logan','908422930690','73084716','nandini_m@example.xn--mgbb9fbpob','Massachusetts 136','$2a$10$WaiPYW2UTvX9h2lG.5W18.BuauW/QJ/rwYY2GkBlnQJW9NWfzr1W2',1,'ADMIN'),
--('Technician','Francoise','Robinson','9951367577407','44809493','sgaurav_baghel@example.ubs','Pennsylvania 864','$2a$10$Ucx13pk7h0a6VNpGD0FJI.2TYR..ek4r.jUeLUqGP7zIDVSAZ14em',1,'TECHNICIAN'),
--('Technician','Sandie','Pierce','5088543501975','73561628','mutlu82@example.jo','Florida 77','$2a$10$rNdMpzgiD7zJfF5d1S6WKORUVXZ21Bs9sNNJwhNmpfmPO6YYyW4ZW',1,'TECHNICIAN'),
--('Technician','Danuta','Wagner','3573903361278','32771239','leemunroe@test.onyourside','Indiana 983','$2a$10$Tlv48P9oWQvQ5ACQEtt1zOfHG7AwpMDMhKmtoiZfozTPXCsbE0TCi',1,'TECHNICIAN'),
--('Technician','Hong','Ellis','559222718892','73236667','syropian@test.cooking','New York 834','$2a$10$P4usHMnaFumCjh9ROtwu3uLFl0.rmg3PGss41aNUB3fHTpcBXCo9e',1,'TECHNICIAN'),
--('Client','Porter','Jacobs','632348530864','30778676','jeremiaha@test.buzz','Ohio 535','$2a$10$jHQNvjl1RK2jZ34JHQ/WiuYkUFlvZiustaWvxebxiItY2dUob.wUy',1,'CLIENT'),
--('Client','Olen','Chapman','501202873710','58754351','soyjavi@test.td','Utah 667','$2a$10$6j7FmHHr.bQ8c1jAMhaygeE4p2QNcqAFcLMAQkDBXSvdKxSNuTWIq',1,'CLIENT'),
--('Client','Brenton','Doyle','9648491165459','49615138','anoff@example.nikon','Missouri 78','$2a$10$3KdciiXvHBKqP9HpQHq4RuG735GqXP3Mo.qqtlmp06.rHilIFOY6C',1,'CLIENT'),
--('Client','Hai','Nunez','2261619234495','29783685','madysondesigns@test.site','Nevada 878','$2a$10$5Dg3GLQYOhwuMod/ZGlvO.ooZPxjbM9MoKp/xG/PHg3zGbDD0T9Dq',1,'CLIENT'),
--('Client','Columbus','George','3735254485422','23424060','beweinreich@test.lpl','Missouri 63','$2a$10$kbA9xVp.R1NNxXWGuwXQZOuWaOU.nOO3RZiAzge4o5lARH8VrhR5i',1,'CLIENT'),
--('Client','Arminda','Evans','2421478367900','48918760','tgerken@test.globo','Maryland 730','$2a$10$o6Yl.biDVqHmeAEEQMhUreWz0gbc/iwgaHhcaCVWeLBvDd7BgSBf2',1,'CLIENT'),
--('Client','Jamey','Knight','9606310246291','11291010','krasnoukhov@test.dentist','California 380','$2a$10$JoS6GshSZuOSLUqeQRv3B.wi2n3f8yRh4Dvxy3HWanmcCOgN1YI4.',0,'CLIENT');


--
--insert into `order`(number, observations, initial_price, final_price, id_client) values
--("ORD001", "Order 1", 100.0, 150.0, 1 ),
--("ORD002", "Order 2", 200.0, 250.0, 2 ),
--("ORD003", "Order 3", 300.0, 350.0, 3 ),
--("ORD004", "Order 4", 400.0, 450.0, 4 ),
--("ORD005", "Order 5", 500.0, 550.0, 5 ),
--("ORD006", "Order 6", 600.0, 650.0, 6 ),
--("ORD007", "Order 7", 700.0, 750.0, 7 ),
--("ORD008", "Order 8", 800.0, 850.0, 8 ),
--("ORD009", "Order 9", 900.0, 950.0, 9 ),
--("ORD010", "Order 10", 1000.0, 1050.0, 10 );

--insert into `state`(state_name) values
--("NEW"),
--("REPAIRED"),
--("DISPATCHED"),
--("DELIVERED"),
--("CANDELLED");
--
--insert into type(type_name) values
--("Laptop"),
--("Phone"),
--("Tablet"),
--("Desktop"),
--("Wearable");

--insert into device(model, serial_number, accessories, description, id_order, id_state, id_type) values
--("Model A", "SN12345", "Charger", "New device",1,1,1 ),
--("Model B", "SN54321", "Cover", "Used device",2,2,2 ),
--("Model C", "SN67890", "Keyboard", "Refurbished device",3,3,3 ),
--("Model D", "SN09876", "Mouse", "Old device",4,4,4 ),
--("Model E", "SN11223", "Stand", "Damaged device",5,5,5 ),
--("Model F", "SN44556", "Headphones", "New device",6,1,1 ),
--("Model G", "SN77889", "Cable", "Used device",7,2,2 ),
--("Model H", "SN99000", "Adapter", "Refurbished device",8,3,3 ),
--("Model I", "SN11334", "Battery", "Old device",9,4,4 ),
--("Model J", "SN55667", "Screen Protector", "Damaged device",10,5,5 );

--insert into action_user(name) values
--("CREATE"),
--("READ"),
--("UPDATE"),
--("DELETE"),
--("EXPORT");

--INSERT into user_changes(id_action_user,id_technician) values
--(1,1 ),
--(2,2 ),
--(3,3 ),
--(4,4 ),
--(5,5 ),
--(1,6 ),
--(2,7 ),
--(3,8 ),
--(4,9 ),
--(5,10 );