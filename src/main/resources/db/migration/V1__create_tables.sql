CREATE TABLE client (
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE
);

create table purchase (
    id serial PRIMARY KEY,
    prushace_date TIMESTAMP NOT NULL,
    total_purchase FLOAT CHECK (total_purchase > 0) NOT NULL,
    id_client INTEGER REFERENCES client(id)
);

create table product (
    id serial PRIMARY KEY,
    product_code INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    price FLOAT NOT NULL
);
create table purchase_product (
    id_product INTEGER REFERENCES product(id),
    id_purchase INTEGER REFERENCES purchase(id),
    PRIMARY KEY(id_purchase, id_product)
);
