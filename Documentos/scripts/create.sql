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
	product_id INT NOT NULL,
    composition_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (composition_id) REFERENCES composition(id)
);

CREATE TABLE product_has_optionsComposition (
	product_id INT NOT NULL,
    composition_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (composition_id) REFERENCES composition(id)
);

CREATE TABLE categoria (
    id		INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name 	VARCHAR(30) NOT NULL
);

