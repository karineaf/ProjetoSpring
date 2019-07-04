drop database gfood;

create database gfood;
use gfood;

create table cliente(
	id integer not null auto_increment,
    nome VARCHAR(50) not null,
    endereco varchar(200),
    primary key(id)
);

INSERT INTO cliente (nome, endereco, data_nasc) VALUES ("Karine Farias", "Rua Gomes Leal, 298", '1999-01-25');
INSERT INTO cliente (nome, endereco, data_nasc) VALUES ("Marilia Angela", "Rua Eugenio de Freitas, 525", '1997-12-01');
INSERT  INTO cliente(nome, endereco, data_nasc) VALUES ("Karine Farias", "Rua Nicola Tolentino de Almeida, 453", '1998-02-26');
INSERT  INTO cliente(nome, endereco, data_nasc) VALUES ("Vanusa Saravalle", "Av Parada Pinto, 1366", '1998-02-26');
select * from cliente;

ALTER TABLE cliente
    ADD data_nasc DATE NULL;

ALTER TABLE produto
    ADD preco DECIMAL(6, 2) NULL;


create table produto(
    id integer not null auto_increment,
    nome VARCHAR(50) not null,
    descricao varchar(200),
    primary key(id)
);

INSERT INTO produto (nome, descricao, preco) VALUES ("X-Burguer", "Lanche composto por 2 hamburgueres, queijo, alface", 17.50);
INSERT INTO produto (nome, descricao, preco) VALUES ("HotDog", "Lanche composto por 1 salsicha, batata palha e ketchup", 19.99);
INSERT INTO produto (nome, descricao, preco) VALUES ("Combo X-Burger", "Lanche (2 hamburgueres, queijo, alface) + Lata de Refrigerante de 300ml", 25.50);
INSERT INTO produto (nome, descricao, preco) VALUES ("Açaí", "Copo de açaí de 700ml contendo adicional de leite condensado", 9.99);
INSERT INTO produto (nome, descricao, preco) VALUES ("Água", "Copo de água sem gás de 250ml", 2.99);
INSERT INTO produto (nome, descricao, preco) VALUES ("Refrigerante", "Lata de Refrigerante de 350ml", 4.99);

select * from produto;
