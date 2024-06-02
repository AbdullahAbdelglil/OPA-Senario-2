drop schema if exists `web_service`; 
create schema `web_service`;
use web_service;

create table `user` (
	`id` int not null auto_increment,
    `username` varchar(50) not null,
    `role` varchar(50) not null,
    
    primary key(`id`)
);

create table `page` (
	`id` int not null auto_increment,
    `path` varchar(250) not null,
    
    primary key (`id`)
);

create table `priviliges` (
	`id` int not null auto_increment,
    `user_id` int,
    `page_id` int,
    
    primary key (`id`),
    foreign key(`user_id`) references user(`id`),
    foreign key(`page_id`) references page(`id`)
);

insert into user values (1,'Abdullah', 'admin'),(2,'Omar', 'user'),(3,'Ahmed', 'accountant');
insert into page values (1,'/user'),(2,'/accountant'),(3,'/admin'),(4,'/general');
insert into priviliges values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,4),(7,3,2),(8,3,4);