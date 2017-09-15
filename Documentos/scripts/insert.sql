use db_mastermenu;

INSERT INTO composition (name) VALUES ('arroz');
INSERT INTO composition (name) VALUES ('feijao');
INSERT INTO composition (name) VALUES ('batata frita');
INSERT INTO composition (name) VALUES ('bife de frango');
INSERT INTO composition (name) VALUES ('bife bovino');
INSERT INTO composition (name) VALUES ('laranja');
INSERT INTO composition (name) VALUES ('uva');
INSERT INTO composition (name) VALUES ('abacaxi');
INSERT INTO composition (name) VALUES ('agua');
INSERT INTO composition (name) VALUES ('batata palha');
INSERT INTO composition (name) VALUES ('iscas de carne ao molho');
SELECT * FROM composition;


INSERT INTO product (name, price) VALUES ("SUCO DE UVA", 3.50);
INSERT INTO product (name, price) VALUES ("SUCO DE LARANJA", 3.50);
INSERT INTO product (name, price) VALUES ("COCA COLA", 3.50);
INSERT INTO product (name, price) VALUES ("ALAMINUTA", 13.90);
INSERT INTO product (name, price) VALUES ("STROGONOFF", 16.00);
SELECT * FROM product;


INSERT INTO product_has_composition (id_product, id_composition) VALUES (1, 8);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (1, 7);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (2, 9);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (2, 6);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (4, 1);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (4, 2);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (4, 3);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (4, 4);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (5, 1);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (5, 10);
INSERT INTO product_has_composition (id_product, id_composition) VALUES (5, 11);
SELECT * FROM product_has_composition;


INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (1, 8);
INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (1, 7);
INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (2, 8);
INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (2, 6);
INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (4, 5);
INSERT INTO product_has_optionsComposition (id_product, id_composition) VALUES (4, 10);
SELECT * FROM product_has_optionscomposition;


