create database doceafeto;
use doceafeto;
 
create table produto(
	id int auto_increment primary key,
	nome varchar (50),
	descricao varchar(100),
	preco double,
	categoria varchar(50),
	imagem varchar(255),
    ativo int DEFAULT 1
);
 
create table pedido(
	id int auto_increment primary key,
	total double,
	sub_total double,
	quantidade int,
	cep varchar (8),
	numero int,
	status varchar (30),
	frete double,
	data DATE DEFAULT (CURRENT_DATE),
	nome varchar (50),
	sobrenome varchar(50),
	telefone varchar(11),
	id_produto int,
	constraint fk_produto foreign key (id_produto) references produto(id)
);

INSERT INTO produto (nome, descricao, preco, categoria, imagem) VALUES 
	('Morango do amor','Morango enrolado no brigadeiro de ninho com casquinha crocante',15,'Doce','https://www.comidaereceitas.com.br/wp-content/uploads/2025/08/Morango-do-amor-780x520.jpg'),
	('Brigadeiro','Docinho de chocolate 50% enrolado no granulado',3.5,'Doce','https://i.pinimg.com/originals/fd/94/55/fd945589a153e2997be51789eb81a0b6.png'),
	('Cone de Maracujá','Cone de sorvete recheado com creme de marácujá coberto por chocolate',10,'Doce','https://tataamaraloficial.com/wp-content/uploads/2024/01/Cone-Trufado-de-maracuja-blog-tata-amaral.webp'),
	('Bolo de Pote Cenoura','Bolo de cenoura com cobertura e recheio de choocolate em um pote, acompanha colher.',20,'Bolo','https://deliciagostosa.com/wp-content/uploads/bolo-de-cenoura-no-pote.jpg'),
	('Brigadeiro de Ninho','Docinho cremoso enrolado no leite Ninho',6,'Doce','https://s2-receitas.glbimg.com/lNUj65izt6BZwhmSBZg2bMeI4UY=/1366x0/filters:format(jpeg)/https://i.s3.glbimg.com/v1/AUTH_1f540e0b94d8437dbbc39d567a1dee68/internal_photos/bs/2024/r/w/CJFAsBSCO68XnboxmdHA/brigadeiro-de-leite-ninho-e-nutella.jpg'),
	('Cone de Ninho com Nutella','Cone de sorvete recheado com creme de Ninho e Nutella coberto com chocolate',12,'Doce','https://i.pinimg.com/736x/36/62/1f/36621f82b65d466a1d1838ad75c21859.jpg'),
	('Torta de Limão','Torta com creme de limão e cobertura de chantily e raspinhas de limão.',13.5,'Torta','https://www.finafarinha.com.br/wp-content/uploads/2021/03/torta_doce_limao-1-scaled.jpg'),
	('Banoffe','Doce de Banana com doce de leite e chantily',13.5,'Torta','https://www.unileverfoodsolutions.com.br/dam/global-ufs/mcos/SLA/calcmenu/recipes/BR-recipes/desserts-&-bakery/torta-banoffe/main-header.jpg'),
	('Bolo de Pote Ninho com Morango','Bolo simples com recheio de creme de ninho com pedaços picados de morango',10,'Bolo','https://receitatodahora.com.br/wp-content/uploads/2017/06/bolo-de-pote-de-leite-ninho-com-morango-1200x900.jpg'),
	('Surpresa de Morango','Creme de ninho com morangos picados e ganache de chocolate',14.5,'Doce','https://teamodoce.com.br/wp-content/uploads/2025/01/bombom-de-morango-na-travessa-simples.webp'),
	('Bombom de morango','Creme de ninho enrolado no morango passado no chocolate blend',12,'Doce','https://thf.bing.com/th/id/OIP.ge0GUTW06Ie33O3p8T8sqQHaEj?w=265&h=180&c=7&r=0&o=7&cb=thfc1falcon&pid=1.7&rm=3'),
    ('Torta Maracujá','Torta com creme de maracujá e com sementes.',12,'Torta','https://guiadacozinha.com.br/wp-content/uploads/2019/10/tortinha-maracuja.jpg'),
	('Brownie','Bolo de chocolate cremoso e molhadinho',15,'Bolo','https://thf.bing.com/th/id/OIP.APFLyRCEfxk_0OBatKcQYgHaE8?w=254&h=180&c=7&r=0&o=7&cb=thfc1falcon&pid=1.7&rm=3'),
	('Torta de Morango','Torta com creme de branco e cobertura sabor morango com pedaços.',12,'Torta','https://cdn.casaeculinaria.com/wp-content/uploads/2024/05/04151858/Tortinha-de-morango-1.webp'),
	('Surpresa de Uva','Creme de ninho enrolado no uva passado no chocolate blend',12,'Doce','https://cdn0.tudoreceitas.com/pt/posts/5/2/0/uva_coberta_2025_600.jpg'),
	('Bolo pudim','Bolo com uma camada de pudim cremoso com recheio de doce de leite',25,'Bolo','https://thf.bing.com/th/id/OIP.NtsMj33gFqT4anFSwOi3bgHaG_?w=184&h=180&c=7&r=0&o=7&cb=thfc1falcon&pid=1.7&rm=3'),
	('Cone de Oreo','Cone com recheio de creme com pedaços do biscoito Oreo coberto com chocolate.',13.5,'Doce','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp4amXdoBUYfSivPwsApkYgmE0ThyhErfkExNQHOlmRt2vR7GhGPYEXHU&s=10'),
    ('Torta de Brigadeiro','Torta com massa e recheio de chocolate e brigadeiros',20,'Torta','https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgijfyUlv-E2mrpAVwRlQ0A3tGhzQLN8dYSGhJygATVtDItBQCjrHbMIq8k4WoOFEUzcfF6OjbfNspDUP1qYuG5F7TqqQnhhWKesC420tJgzNU0NfvT8fqVrp5kQrIsU-kv0hMd-TmEHOQts93N8ajub38JN-BNjxwNAvhdusvv5X1dR88E3mOh6pmq/s2000/tor');