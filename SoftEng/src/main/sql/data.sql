CREATE TABLE `crowdsporting`.`provider` (
`provider_id` INT NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(100) NOT NULL,
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
PRIMARY KEY (`provider_id`)
) ENGINE = InnoDB;

CREATE TABLE `crowdsporting`.`activity` (
`activity_id` INT NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(100) NOT NULL,
`description` VARCHAR(500),
`provider_id` INT NOT NULL UNIQUE,
`likes` INT ,
PRIMARY KEY (`activity_id`),
FOREIGN KEY (`provider_id`) ,
REFERENCES provider(`provider_id`)
ON DELETE CASCADE
ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE `crowdsporting`.`users` (
`username` VARCHAR(100) NOT NULL UNIQUE ,
`pic` LONGBLOB ,
`fname` VARCHAR(100) NOT NULL,
`lname` VARCHAR(100) NOT NULL,
`points` INT NOT NULL DEFAULT 0,
`mail` VARCHAR(320) NOT NULL ,
PRIMARY KEY (`username`)
) ENGINE = InnoDB;