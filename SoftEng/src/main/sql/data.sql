CREATE DATABASE `crowdsporting`;

CREATE TABLE `crowdsporting`.`provider` (
`name` VARCHAR(100) NOT NULL UNIQUE,
`street`  VARCHAR(100) NOT NULL,
`number` INT NOT NULL,
`postal_code` INT NOT NULL ,
`city` VARCHAR(100) NOT NULL ,
`category` ENUM(
    'gym',
    'dance',
    'sports team',
    'yoga',
    'pilates',
    'other'
  ),
`mail` VARCHAR(320) ,
`phone` VARCHAR(15) NOT NULL,
`website` VARCHAR(320) ,
PRIMARY KEY (`name`)
) ENGINE = InnoDB;

CREATE TABLE `crowdsporting`.`activity` (
`activity_id` INT NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(100) NOT NULL,
`description` VARCHAR(500),
`prov_name` VARCHAR(100) NOT NULL,
`likes` INT ,
PRIMARY KEY (`activity_id`),
FOREIGN KEY (`prov_name`) REFERENCES provider(`name`)
ON DELETE CASCADE
ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE `crowdsporting`.`users` (
`username` VARCHAR(100) NOT NULL UNIQUE ,
`fname` VARCHAR(100) NOT NULL,
`lname` VARCHAR(100) NOT NULL,
`points` INT NOT NULL DEFAULT 0,
`mail` VARCHAR(320) NOT NULL ,
PRIMARY KEY (`username`)
) ENGINE = InnoDB;

INSERT INTO crowdsporting.provider (name, street, number,  postal_code, city, category, mail , phone, website)
VALUES ('Get fit', 'Iera odos', 199, 12245, 'Athens', 'gym', 'getfitgym@gmail.com' , '2105999321' , null);

INSERT INTO crowdsporting.activity ( name, description, prov_name ,likes)
VALUES ( 'yoga', 'συνδρομή ενός μήνα, 4 μαθήματα την εβδομάδα', 'Get fit', 0);

INSERT INTO crowdsporting.users (username, fname, lname, points, mail)
VALUES ('dummy', 'John', 'Doe', 0 , 'j_doe@gmail.com');