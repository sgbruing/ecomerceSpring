create database loja;

create table compra (
    id serial PRIMARY KEY,
    data_compra TIMESTAMP NOT NULL,
    cpf_cliente varchar NOT NULL,
    valor_total_compra double precision NOT NULL     
);

create table users(
                      username varchar not null primary key,
                      password varchar not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar not null,
                             authority varchar not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);

create table produto (
                         id serial PRIMARY KEY,
                         codigo varchar NOT NULL,
                         nome varchar NOT NULL,
                         preco double precision CHECK(preco > 0),
                         qtde_disponivel int NOT NULL
);

create table compra_produto(
    id_produto integer references produto(id),
    id_compra integer references compra(id),
    quantidade integer not null default 1,
    primary key(id_produto, id_compra)
);

create unique index ix_auth_username on authorities (username,authority);

--A senha é admin

INSERT INTO users (username, password, enabled)
values ('admin',
        '$2a$12$K.vLLdlxuoJPUyfYlqb9ouKrohdJOioDdYBL3TfQJpIxsT2ChcHVW',
        true);

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');

