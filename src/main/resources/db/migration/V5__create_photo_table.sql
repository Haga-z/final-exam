use `exam`;

CREATE TABLE IF NOT EXISTS `photos` (
    `id` int auto_increment NOT NULL,
    `name` varchar(100) NOT NULL,
    `photo` longblob  NOT NULL,
    `place_id` int NOT NULL ,
    PRIMARY KEY (`id`),
    foreign key (place_id)references `places`(id)
);