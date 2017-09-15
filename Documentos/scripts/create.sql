USE db_mastermenu;

CREATE TABLE composition (
	id	 INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE product (
	id 				INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name 			VARCHAR(30) NOT NULL,
	description 	VARCHAR(100),
	price			DOUBLE NOT NULL
);

CREATE TABLE product_has_composition (
	id_product INT NOT NULL,
    id_composition INT NOT NULL,
    FOREIGN KEY (id_product) REFERENCES product(id),
    FOREIGN KEY (id_composition) REFERENCES composition(id)
);

CREATE TABLE product_has_optionsComposition (
	id_product INT NOT NULL,
    id_composition INT NOT NULL,
    FOREIGN KEY (id_product) REFERENCES product(id),
    FOREIGN KEY (id_composition) REFERENCES composition(id)
);

