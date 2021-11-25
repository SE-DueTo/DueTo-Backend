CREATE TABLE `users` (
     `user_id` UNIQUE INT NOT NULL AUTO_INCREMENT,
     `username` TEXT NOT NULL,
     `email` TEXT NOT NULL,
     `avatar_url` TEXT,
     `password` TEXT NOT NULL,
     PRIMARY KEY (`user_id`)
);
