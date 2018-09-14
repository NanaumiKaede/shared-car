create table car
(
	id int auto_increment
		primary key,
	address_id int not null,
	car_number char(10) not null,
	type varchar(16) default '电能驱动' null,
	remain_energy double(12,2) default '100.00' null,
	car_condition varchar(10) default '可租赁' null,
	total_mile double(12,2) default '0.00' null,
	total_energy double(12,2) default '55.00' null,
	total_income double(12,2) default '0.00' null,
	total_cost double(12,2) default '0.00' null,
	constraint car_address_id_fk
		foreign key (address_id) references address (id)
)
;

create index car_address_id_fk
	on car (address_id)
;

