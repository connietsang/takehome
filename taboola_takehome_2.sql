CREATE TABLE products
(product_name VARCHAR PRIMARY KEY,
 category VARCHAR NOT NULL,
 date_product_added DATE NOT NULL,
 employee_id VARCHAR NOT NULL
);

CREATE TABLE product_price_change_log(
	product_name VARCHAR,
	old_price NUMERIC CHECK(old_price >= 0),
	new_price NUMERIC NOT NULL CHECK (new_price >=0),
	old_discount NUMERIC(5,2) CHECK(old_discount between 0 and 1),
	new_discount NUMERIC(5,2) NOT NULL CHECK(new_discount between 0 and 1),
	time_of_update TIMESTAMP,
	operation_performed VARCHAR,
	employee_id VARCHAR,
	PRIMARY KEY (product_name,time_of_update,employee_id),
	FOREIGN KEY product_name REFERENCES products
);

CREATE TABLE product_price(
	product_name VARCHAR REFERENCES products,
	product_price NUMERIC NOT NULL CHECK(product_price >= 0),
	current_discount DECIMAL(5,2)DEFAULT 0 CHECK(current_discount between 0 and 1),
	time_of_update TIMESTAMP,
	employee_id VARCHAR,
	PRIMARY KEY(product_name),
	FOREIGN KEY (product_name, time_of_update,employee_id) REFERENCES 
	product_price_change_log(product_name,time_of_update,employee_id)
);


SELECT  P.product_name, P.category, PR.product_price,PR.time_of_update, PR.employee_id
FROM products as P
JOIN product_price as PR
ON P.product_name = PR.product_name;