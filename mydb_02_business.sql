create table business
(
	id int auto_increment
		primary key,
	user_id int not null,
	car_id int not null,
	type varchar(10) not null,
	work_time datetime not null,
	car_img varchar(100) null,
	mile double(10,2) null,
	energy_cost double(10,2) null,
	address_id int null,
	other_address varchar(255) null,
	response tinyint(1) default '0' null,
	constraint business_user_id_fk
		foreign key (user_id) references user (id),
	constraint business_car_id_fk
		foreign key (car_id) references car (id),
	constraint business_address_id_fk
		foreign key (address_id) references address (id)
)
;

create index business_address_id_fk
	on business (address_id)
;

create index business_car_id_fk
	on business (car_id)
;

create index business_user_id_fk
	on business (user_id)
;

