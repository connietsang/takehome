CREATE TABLE products
(product_name varchar NOT NULL PRIMARY KEY,
 category varchar NOT NULL
);

CREATE TABLE product_price_history(
	product_name VARCHAR REFERENCES products,
	product_price NUMERIC NOT NULL CHECK(product_price >= 0),
	start_date DATE,
	end_date DATE,
	PRIMARY KEY (product_name,start_date) 
);

SELECT  P.product_name, P.category, Pr.product_price,PR.start_date, PR.end_date
FROM products as P
JOIN product_price_history as PR
ON P.product_name = PR.product_name;