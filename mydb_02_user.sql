create table user
(
	id int auto_increment
		primary key,
	username varchar(20) not null,
	password varchar(32) not null,
	name varchar(10) null,
	sex varchar(4) null,
	age int null,
	phone char(11) null,
	id_card char(18) null,
	driver_id char(12) null,
	reg_time date not null,
	user_condition varchar(16) default '审核中' null
)
;

