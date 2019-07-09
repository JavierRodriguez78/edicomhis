CREATE TABLE `doctors` (
  `doctor_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collegiatenumber` varchar(255) DEFAULT NULL,
  `created_at` date NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `UK_ermnkgr2i1xg9nrc8uay62srx` (`collegiatenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `patients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `medical_record` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `doctor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g92qkb2w7dg55t55h2efcf8v0` (`medical_record`),
  KEY `FKperqpk72jxd90l8yq7qf5fsx0` (`doctor_id`),
  CONSTRAINT `FKperqpk72jxd90l8yq7qf5fsx0` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

