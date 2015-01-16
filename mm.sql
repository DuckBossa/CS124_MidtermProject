CREATE database IF NOT EXISTS MM;

use MM;

CREATE TABLE IF NOT EXISTS MMPack(
	ID INTEGER(100) AUTO_INCREMENT NOT NULL,
	price FLOAT(10,2) NOT NULL,
	quantity INTEGER(100) NOT NULL,
	size VARCHAR(255) NOT NULL,
	net_weight FLOAT(10,2) NOT NULL,
	kind VARCHAR(255) NOT NULL,
	CONSTRAINT MMPack_pk PRIMARY KEY(ID)
	);
	
CREATE TABLE IF NOT EXISTS Sales(
	country VARCHAR(255) NOT NULL,
	profit FLOAT(10,2) NOT NULL,
	CONSTRAINT Sales_pk PRIMARY KEY(country)
);

INSERT INTO MMPack(`price`, `quantity`, `size`, `net_weight`, kind) VALUES
(55.99,100, 'small', 47.9, 'milk chocolate'),
(524,20, 'party', 765.5, 'milk chocolate'),
(540.95, 20, 'party', 1190.7, 'peanut'),
(125.75, 70, 'small', 32.3, 'pretzel'),
(74.50,50,'small', 46.2, 'peanut butter');
;

INSERT INTO Sales(`country`, `profit`) VALUES
('Philippines', 345678.99),
('Malaysia', 12321322.67),
('Thailand', 66782321.11);

