CREATE DATABASE `crowdsporting`;

DROP TABLE IF EXISTS `crowdsporting`.`shops`;
CREATE TABLE `crowdsporting`.`shops`
(
  `id`          int(11)      NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255) NOT NULL UNIQUE,
  `address`     VARCHAR(255) NOT NULL,
  `lng`         DOUBLE,
  `lat`         DOUBLE,
  `withdrawn`   bit(1) NOT NULL DEFAULT b'0',
  `mail`        VARCHAR(320),
  `phone`       VARCHAR(15)  NOT NULL,
  `website`     VARCHAR(320),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `crowdsporting`.`products`;
CREATE TABLE `crowdsporting`.`products`
(
  `id`          int(11)      NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) NOT NULL,
  `description` mediumtext,
  `category`    ENUM ('Γυμναστήριο', 'χορός', 'πολεμικές τέχνες', 'Paintball', 'Personal Training', 'Rafting', 'Αναρρίχηση','Γιόγκα', 'Ιππασία', 'Καταδύσεις' , 'Κολύμβηση', 'Πιλάτες', 'Ποδόσφαιρο', 'Σκι' ,'Τένις', 'άλλο'),
  `withdrawn`   bool         NOT NULL DEFAULT false,
  `likes`       int(11),
  `prov_id`     int(11)      NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`prov_id`) REFERENCES `crowdsporting`.`shops` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `crowdsporting`.`product_tags`;
CREATE TABLE `crowdsporting`.`product_tags`
(
  `pid` int(11)      NOT NULL DEFAULT '0',
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`pid`, `tag`),
  FOREIGN KEY (`pid`) REFERENCES `crowdsporting`.`products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `crowdsporting`.`shop_tags`;
CREATE TABLE `crowdsporting`.`shop_tags`
(
  `sid` int(11)      NOT NULL DEFAULT '0',
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`sid`, `tag`),
  FOREIGN KEY (`sid`) REFERENCES `crowdsporting`.`shops (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `crowdsporting`.`user`;
CREATE TABLE `crowdsporting`.`users`
(
  `username` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(12)  NOT NULL,
  `points`   INT          NOT NULL DEFAULT 0,
  `email`     VARCHAR(320) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `crowdsporting`.`prices`;
CREATE TABLE `crowdsporting`.`prices`
(
  `id`          int(11)      NOT NULL AUTO_INCREMENT,
  `act_id`      int(11) NOT NULL,
  `date`    DATE NOT NULL ,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`act_id`) REFERENCES `crowdsporting`.`products` (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;