# commonly used sqls in this project

create database playground;
create user 'user1'@'%' identified by 'password';
grant all on playground.* to 'user1'@'%';

select * from mysql.user;
select * from playground.topic;

