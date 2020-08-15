use `exam`;

CREATE TABLE IF NOT EXISTS `reviews`(
    `id` int auto_increment NOT NULL,
    `text` varchar(500) NOT NULL,
    `mark` double not null ,
    `date` datetime not null ,
    `user_id` int NOT NULL ,
    `place_id` int NOT NULL ,
    PRIMARY KEY (`id`),
    foreign key (user_id)references `users`(id),
    foreign key (place_id)references `places`(id)
);


