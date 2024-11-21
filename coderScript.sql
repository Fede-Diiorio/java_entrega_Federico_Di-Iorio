CREATE DATABASE IF NOT EXISTS coderhouse;
USE coderhouse;

SELECT * FROM carts; 
SELECT * FROM categories;
SELECT * FROM clients;
SELECT * FROM products;
SELECT * FROM products_carts;
SELECT * FROM tickets;
SELECT * FROM tickets_products;

INSERT INTO categories (name, slug) VALUES 
("Colgantes", "colgante"),
("Peluches", "peluche"),
("Remeras", "remera"),
("Varitas", "varita");

INSERT INTO products (name, image, description, code, stock, price, category)
VALUES 
('Peluche de Draco Malfoy', 'https://64.media.tumblr.com/cf47ca5aa23b1c1885c90896722ffbf2/e4e5daa2b2c30da5-96/s1280x1920/290f7f783fceaace3b9ddcc046fd939e92bcab04.webp', 'Experimenta la elegancia y el encanto con este peluche de Draco Malfoy...', 'abc123', 15, 10, 1),
('Colgante de Reliquias de la Muerte', 'https://64.media.tumblr.com/1542a41ea1d5b2609f0396d9c95421b5/e4e5daa2b2c30da5-69/s1280x1920/8ef53930b362296308a277d3615c4c5c32eee8be.webp', 'Un colgante místico que representa las tres Reliquias de la Muerte...', 'def456', 28, 4, 2),
('Remera de Reliquias de la Muerte', 'https://64.media.tumblr.com/4f243f75cb4fcf6de73a31fd04668bdd/e4e5daa2b2c30da5-26/s1280x1920/1a47acc567e938436fd7dc29219a5f6683f0f1bd.webp', 'Una remera intrigante con el símbolo de las Reliquias de la Muerte...', 'ghi789', 59, 11, 3),
('Remera de Plataforma 9 3/4', 'https://64.media.tumblr.com/9d79e480a58174fb3aa80b2f4a4b844a/e4e5daa2b2c30da5-10/s1280x1920/19d78e191cb3ac67d170164605cdd636584c614d.webp', 'Una elegante remera con el emblema de la Plataforma 9 3/4...', 'jkl012', 51, 11, 3),
('Peluche de Harry Potter', 'https://64.media.tumblr.com/c11a9b63ab4893ab6c64d10fb7abf0f4/e4e5daa2b2c30da5-8c/s1280x1920/a0df4a07ce6e9002456f305882f707a4dab39ea1.webp', 'Lleva contigo la magia de Hogwarts con este encantador peluche de Harry Potter...', 'mno345', 15, 10, 1),
('Colgante de Harry Potter', 'https://64.media.tumblr.com/b9eaa5c2e96a18cd858475aa2a1480ea/e4e5daa2b2c30da5-d6/s1280x1920/b1789400299ac4a8a94237c1e7e3cee0862a72a4.webp', 'Un encantador colgante inspirado en el famoso mago Harry Potter...', 'pqr678', 26, 4, 2),
('Remera de Emblema de Hogwarts', 'https://64.media.tumblr.com/0bb952ad84c37617101262eba25ab3ab/e4e5daa2b2c30da5-40/s1280x1920/3462e50012b63a315ad8a0f64d9cfeb0e979f13e.webp', 'Luce con orgullo el emblema de Hogwarts con esta vibrante remera...', 'stu901', 52, 11, 3),
('Peluche de Lord Voldemort', 'https://64.media.tumblr.com/ddf5137efea7e88f45e740d28b443844/e4e5daa2b2c30da5-f5/s1280x1920/2c8247dd8b36022d946e780782dfcb3a59d3ede9.webp', 'Un adorable peluche que captura la esencia misteriosa y tenebrosa de Lord Voldemort...', 'vwx234', 15, 10, 1),
('Colgante de Plataforma 9 3/4', 'https://64.media.tumblr.com/bbb6c5eb04a61524345537f7b7d16f45/e4e5daa2b2c30da5-19/s1280x1920/5307da6ce5d909e0a0cecf056e7791465af8ad16.webp', 'Un encantador colgante que representa la entrada a la Plataforma 9 3/4...', 'yza567', 30, 4, 2),
('Varita de Harry Potter', 'https://64.media.tumblr.com/ae73700e52b4f38e40fdb14f29271bdb/e4e5daa2b2c30da5-c7/s1280x1920/930cba345015eba1fcf765985da4d53cf12e98de.webp', 'La icónica varita de Harry Potter, conocida por sus hazañas...', 'bcd890', 15, 20, 4),
('Varita de Sauco', 'https://64.media.tumblr.com/f3b2e0b768779f4bb04ae42b90f4e09c/e4e5daa2b2c30da5-29/s1280x1920/b716fcef03932504d9a50b3725e60d0280a99f5f.webp', 'Una poderosa varita esculpida en madera de saúco...', 'efg123', 15, 20, 4),
('Varita de Voldemort', 'https://64.media.tumblr.com/cfa2e898bc20b8b9e04096c317143c2c/e4e5daa2b2c30da5-22/s1280x1920/984b843dbdc05a1cd7cb769a181e1f817e38ff82.webp', 'La oscura varita de Lord Voldemort...', 'hij456', 15, 20, 4);

INSERT INTO products (name, image, description, code, stock, price)
VALUES 
('Test Categoría', 'Sin Imagen', 'Descripción', 'hij456f', 15, 20);

INSERT INTO carts(id) VALUES
(1),(2),(3);

INSERT INTO clients (name, lastname, docnumber, cart_id)
VALUES
("Federico", "Di Iorio", "38831646", 1),
("Gabriel", "Navarro", "42831646", 2),
("Alejandro", "Di Stefano", "20831646", 3);

SELECT p.id, p.name, p.code, p.description, p.image, p.price, p.stock, c.slug AS category FROM products AS p INNER JOIN categories AS c ON c.id = p.category;
