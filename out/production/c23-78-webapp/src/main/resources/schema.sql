DROP DATABASE IF EXISTS `track_my_fix_db`;
<<<<<<< HEAD
CREATE DATABASE if not exists `track_my_fix_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
=======
CREATE DATABASE if not exists `track_my_fix_db`; -- /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3

use `track_my_fix_db`;

-- track_my_fix_db.`user` definition

CREATE TABLE `user` (
<<<<<<< HEAD
  `discriminator` varchar(31) NOT NULL,
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `address` varchar(250) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT(CURRENT_TIMESTAMP),
  `dni` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` char(70) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role` enum('ADMIN','CLIENT','TECHNICIAN') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UKjq0ta6mef3p0o47ysw6sflcdl` (`dni`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======
  `active` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL default current_timestamp(6),
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `dni` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `discriminator` varchar(31) NOT NULL,
  `email` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` char(100) DEFAULT NULL,
  `address` varchar(250) NOT NULL,
  `role` enum('ADMIN','CLIENT','TECHNICIAN') NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UKjq0ta6mef3p0o47ysw6sflcdl` (`dni`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3


-- track_my_fix_db.`action` definition

CREATE TABLE `action` (
  `id_action` bigint NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_action`),
  UNIQUE KEY `UKnb2hivsac5gi4ptflwt8lnfkh` (`action_name`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3

-- track_my_fix_db.action_user definition

CREATE TABLE `action_user` (
  `id_action_user` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_action_user`),
  UNIQUE KEY `UKmwn1xb09qcaey65evjg8k8ihh` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- track_my_fix_db.`order` definition

CREATE TABLE `order` (
  `id_order` bigint NOT NULL AUTO_INCREMENT,
<<<<<<< HEAD
  `observations` text,
  `created_at` datetime(6) NOT NULL DEFAULT(CURRENT_TIMESTAMP),
  `final_price` decimal(10,2) DEFAULT NULL,
  `initial_price` decimal(10,2) DEFAULT NULL,
  `number` varchar(25) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
=======
  `final_price` decimal(10,2) DEFAULT NULL,
  `initial_price` decimal(10,2) DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `number` varchar(25) NOT NULL,
  `observations` text,
  `created_at` datetime(6) NOT NULL default current_timestamp(6),
  `updated_at` datetime(6) DEFAULT NULL,
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
  PRIMARY KEY (`id_order`),
  UNIQUE KEY `UKb7wobkdwgx8ml5lemn0xr5a0m` (`number`),
  KEY `FKdl6uu1yeapd67g53j7frsun9j` (`id_client`),
  CONSTRAINT `FKdl6uu1yeapd67g53j7frsun9j` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_user`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- track_my_fix_db.state definition

=======
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- track_my_fix_db.state definition
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
CREATE TABLE `state` (
  `id_state` bigint NOT NULL AUTO_INCREMENT,
  `state_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_state`),
  UNIQUE KEY `UKqtjsbpmp2ejq0753ktldenyqo` (`state_name`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- track_my_fix_db.`type` definition

=======
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- track_my_fix_db.`type` definition
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
CREATE TABLE `type` (
  `id_type` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`),
  UNIQUE KEY `UKf1qndtxdjk0ose1st2rhv1vhx` (`type_name`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

=======
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3

-- track_my_fix_db.device definition

CREATE TABLE `device` (
<<<<<<< HEAD
  `id_device` bigint NOT NULL AUTO_INCREMENT,
  `accessories` varchar(250) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT(CURRENT_TIMESTAMP),
  `description` text,
  `model` varchar(100) NOT NULL,
  `serial_number` varchar(100) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_order` bigint DEFAULT NULL,
  `id_state` bigint DEFAULT NULL,
  `id_type` bigint DEFAULT NULL,
=======
  `created_at` datetime(6) NOT NULL default current_timestamp(6),
  `id_device` bigint NOT NULL AUTO_INCREMENT,
  `id_order` bigint DEFAULT NULL,
  `id_state` bigint DEFAULT NULL,
  `id_type` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `model` varchar(100) NOT NULL,
  `serial_number` varchar(100) NOT NULL,
  `accessories` varchar(250) DEFAULT NULL,
  `description` text,
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
  PRIMARY KEY (`id_device`),
  UNIQUE KEY `UK4776vaiywo1kdis4lp8jkm0av` (`serial_number`),
  KEY `FKe6i24hgvnfhmh3u9qqed7frcx` (`id_order`),
  KEY `FKhmhaml1ehlrb51mkpwnxhtkd4` (`id_state`),
  KEY `FKbtcauwr3l109b9ty0aqkkly9h` (`id_type`),
  CONSTRAINT `FKbtcauwr3l109b9ty0aqkkly9h` FOREIGN KEY (`id_type`) REFERENCES `type` (`id_type`),
  CONSTRAINT `FKe6i24hgvnfhmh3u9qqed7frcx` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`),
  CONSTRAINT `FKhmhaml1ehlrb51mkpwnxhtkd4` FOREIGN KEY (`id_state`) REFERENCES `state` (`id_state`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3

-- track_my_fix_db.movement definition

CREATE TABLE `movement` (
<<<<<<< HEAD
  `id_movement` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `movement_date` datetime(6) NOT NULL,
  `id_action` bigint DEFAULT NULL,
  `id_order` bigint DEFAULT NULL,
  `id_technician` bigint DEFAULT NULL,
  PRIMARY KEY (`id_movement`),
=======
  `id_action` bigint DEFAULT NULL,
  `id_device` bigint DEFAULT NULL,
  `id_movement` bigint NOT NULL AUTO_INCREMENT,
  `id_order` bigint DEFAULT NULL,
  `id_technician` bigint DEFAULT NULL,
  `movement_date` datetime(6) NOT NULL,
  `description` text,
  PRIMARY KEY (`id_movement`),
  UNIQUE KEY `UK1m6xvn0456vyfjpbx6xt942kx` (`id_device`),
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
  UNIQUE KEY `UKpx4bn6ck2y0r6c6w4roo0pmlt` (`id_order`),
  KEY `FK2nnucwyjkkn0lw0jdtk0ffga8` (`id_action`),
  KEY `FK4ir1hearf352rgk75ojhl4575` (`id_technician`),
  CONSTRAINT `FK2nnucwyjkkn0lw0jdtk0ffga8` FOREIGN KEY (`id_action`) REFERENCES `action` (`id_action`),
  CONSTRAINT `FK4ir1hearf352rgk75ojhl4575` FOREIGN KEY (`id_technician`) REFERENCES `user` (`id_user`),
<<<<<<< HEAD
  CONSTRAINT `FKb2e1s3ohqb4x97tvxnwce77bl` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- track_my_fix_db.user_changes definition

CREATE TABLE `user_changes` (
  `id_user_change` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL DEFAULT(CURRENT_TIMESTAMP),
  `id_action_user` bigint DEFAULT NULL,
  `id_technician` bigint DEFAULT NULL,
  PRIMARY KEY (`id_user_change`),
  KEY `FK292swqcg11eokwjvypnpsvxil` (`id_action_user`),
  KEY `FK30qgcn5gunuqf3epwrbg1g47o` (`id_technician`),
  CONSTRAINT `FK292swqcg11eokwjvypnpsvxil` FOREIGN KEY (`id_action_user`) REFERENCES `action_user` (`id_action_user`),
  CONSTRAINT `FK30qgcn5gunuqf3epwrbg1g47o` FOREIGN KEY (`id_technician`) REFERENCES `user` (`id_user`)
=======
  CONSTRAINT `FKb2e1s3ohqb4x97tvxnwce77bl` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`),
  CONSTRAINT `FKmd2n32u9bgedhiyb2y9bg7ekj` FOREIGN KEY (`id_device`) REFERENCES `device` (`id_device`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- track_my_fix_db.user_changes definition
CREATE TABLE `user_change` (
  `created_at` datetime(6) NOT NULL default current_timestamp(6),
  `id_action_user` bigint DEFAULT NULL,
  `id_technician` bigint DEFAULT NULL,
  `id_user_change` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_user_change`),
  KEY `FKqpou1ig4kbbyjv4khluma8mnw` (`id_action_user`),
  KEY `FKojdfroe69ex2yg6jue5jg448f` (`id_technician`),
  CONSTRAINT `FKojdfroe69ex2yg6jue5jg448f` FOREIGN KEY (`id_technician`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKqpou1ig4kbbyjv4khluma8mnw` FOREIGN KEY (`id_action_user`) REFERENCES `action_user` (`id_action_user`)
>>>>>>> ad9ec89d1c18019ec25af1a6af898e822befb3c3
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;