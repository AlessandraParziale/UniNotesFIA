DROP database if exists UniNotesDB;
CREATE DATABASE UniNotesDB;
use UniNotesDB;

-- auto-generated definition
create table Corso
(
    id             int auto_increment,
    nome           char(250) not null,
    descrizione    text      not null,
    nomeProfessore char(40)  not null,
    constraint Corso_id_uindex
        unique (id)
);

alter table Corso
    add primary key (id);


-- auto-generated definition
create table Libretto
(
    id       int auto_increment,
    numEsami int    not null,
    media    double not null,
    crediti  int    not null,
    constraint Libretto_id_uindex
        unique (id)
);

alter table Libretto
    add primary key (id);



-- auto-generated definition
create table Esame
(
    id             int auto_increment,
    nome           char(250) not null,
    voto           float     not null,
    cfu            int       not null,
    dataEsame      datetime  not null,
    nomeProfessore char(30)  not null,
    idLibretto     int       not null,
    constraint Esame_id_uindex
        unique (id),
    constraint idLibretto
        foreign key (idLibretto) references Libretto (id)
);

alter table Esame
    add primary key (id);



-- auto-generated definition
create table Utente
(
    id            int auto_increment,
    nome          char(30)   not null,
    cognome       char(40)   not null,
    cf            char(20)   not null,
    email         char(30)   not null,
    DataDiNascita datetime   not null,
    password      text       not null,
    tipo          tinyint(1) not null,
    idLibretto    int        null,
    constraint Utente_id_uindex
        unique (id),
    magistrale    text,
    constraint Utente_Libretto_id_fk
        foreign key (idLibretto) references Libretto (id)
            on update cascade on delete cascade
);

alter table Utente
    add primary key (id);




-- auto-generated definition
create table MaterialeDidattico
(
    id       int auto_increment,
    nome     char(30) not null,
    pathFile text     not null,
    idCorso  int      not null,
    idUtente int      not null,
    constraint MaterialeDidattico_id_uindex
        unique (id),
    constraint MaterialeDidattico_Corso_id_fk
        foreign key (idCorso) references Corso (id)
            on update cascade on delete cascade,
    constraint MaterialeDidattico_Utente_id_fk
        foreign key (idUtente) references Utente (id)
            on update cascade on delete cascade
);

alter table MaterialeDidattico
    add primary key (id);



-- auto-generated definition
create table ListaPreferiti
(
    idUtente int not null,
    idCorso  int not null,
    primary key (idUtente, idCorso),
    constraint Preferiti_Corso_id_fk
        foreign key (idCorso) references Corso (id)
            on update cascade on delete cascade,
    constraint Preferiti_Utente_id_fk
        foreign key (idUtente) references Utente (id)
            on update cascade on delete cascade
);

