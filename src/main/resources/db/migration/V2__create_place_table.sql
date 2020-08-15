use `exam`;

CREATE TABLE IF NOT EXISTS `places` (
    `id` int auto_increment NOT NULL,
    `title` varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    `main_photo` varchar(200) NOT NULL,
    `user_id` int NOT NULL ,
    PRIMARY KEY (`id`),
    foreign key (user_id)references `users`(id)
    );