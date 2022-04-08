insert into produto (codigo, nome, preco, qtde_disponivel) values ('A123', 'PC', 5000.0, 5);
insert into produto (codigo, nome, preco, qtde_disponivel) values ('B123', 'Iphone', 10000.0, 10);

insert into compra (data_compra, cpf_cliente, valor_total_compra) values ('2022-03-20', '12345',5000.0);
insert into compra (data_compra, cpf_cliente, valor_total_compra) values ('2022-03-21', '12345',15000.0);

-- insert into compra_produto values(1,2);
-- insert into compra_produto values(2,2);
-- insert into compra_produto values(1,1);

--A senha é admin

INSERT INTO users (username, password, enabled)
values ('admin',
        '$2a$12$K.vLLdlxuoJPUyfYlqb9ouKrohdJOioDdYBL3TfQJpIxsT2ChcHVW',
        true);

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
