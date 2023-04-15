drop table if exists user CASCADE;
create table user
(
    id bigint INT AUTO_INCREMENT,
    username varchar(255),
    password varchar(255),
    name varchar(255),
    primary key (id)
);