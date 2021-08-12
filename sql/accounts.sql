create table accounts
(
	id int,
	surname text,
	name text,
	mail text not null,
	username text not null
		constraint accounts_pk
			primary key,
	password TEXT not null,
	birthDate date
);

create index accounts__index_id
	on accounts (id);

