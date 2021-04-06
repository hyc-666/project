drop database if exists stums;
create database stums;
use stums;
create table s_student(
    `s_id` int primary key auto_increment,
    `s_number` varchar(11),
    `s_name` varchar(40),
    `s_password` varchar(60),
    `s_college` varchar(150),
    `s_class` varchar(160)
);

-- 插入测试数据
insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(501,'41709040102','张三丰','user501');

--  id编号从500以后开始,0用作root用户,1-500用作管理员账户
insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(null,'41709040107','步惊云','user502');

insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(null,'41709040111','张无忌','user503');

insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(null,'41709040114','乔峰','user504');

insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(null,'41709040158','令狐冲','user505');

insert into s_student(`s_id`,`s_number`,`s_name`,`s_password`)
values(null,'41709040209','岳不群','user506');

select * from s_student;