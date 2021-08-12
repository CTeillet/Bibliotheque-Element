create table main.types
(
	id int
		constraint id_index
			unique,
	type text not null
		constraint types_pk
			primary key
);

INSERT INTO types (id, type) VALUES (0, 'film');
INSERT INTO types (id, type) VALUES (1, 'image');
INSERT INTO types (id, type) VALUES (2, 'book');
INSERT INTO types (id, type) VALUES (3, 'game');