create table address
(
	id int auto_increment
		primary key,
	address_name varchar(255) not null,
	max int not null,
	current int default '0' not null
)
;

