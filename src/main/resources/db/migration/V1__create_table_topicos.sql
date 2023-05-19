create table topicos(

    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensagem varchar(255) not null,
    data_criacao TIMESTAMP not null,
    status varchar(255) not null,
    autor varchar(255) not null,
    curso varchar(255) not null,

    primary key(id)

);