create table elements
(
	path text,
	title text,
	id INTEGER
		constraint elements_pk
			primary key autoincrement,
	description json not null
);

create unique index elements_emplacement_uindex
	on elements (path);

