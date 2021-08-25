create table accounts
(
    id        int,
    surname   text,
    name      text,
    mail      text not null,
    username  text not null
        constraint accounts_pk
            primary key,
    password  TEXT not null,
    birthDate date
);

create index accounts__index_id
    on accounts (id);

create table elements
(
    path        text not null
        constraint elements_pk
            primary key,
    title       text not null,
    id          INTEGER,
    description json not null,
    type        text not null
);

create unique index elements_emplacement_uindex
    on elements (path);

create table types
(
    id   int
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