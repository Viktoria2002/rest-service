CREATE TABLE orders
(
    id               bigint NOT NULL,
    date             date,
    discount         numeric,
    shipping_address character varying,
    user_id          bigint
);

CREATE SEQUENCE orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER SEQUENCE orders_id_seq OWNED BY orders.id;

CREATE TABLE product_order
(
    id         bigint NOT NULL,
    product_id bigint NOT NULL,
    order_id   bigint NOT NULL
);

CREATE SEQUENCE product_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE product_order_id_seq OWNED BY product_order.id;

CREATE TABLE products
(
    pr_id       bigint NOT NULL,
    name        character varying,
    description character varying,
    price       numeric
);

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE products_id_seq OWNED BY products.pr_id;

CREATE TABLE users
(
    id         bigint NOT NULL,
    last_name  character varying,
    first_name character varying,
    email      character varying,
    password   character varying
);

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE users_id_seq OWNED BY users.id;

ALTER TABLE ONLY orders ALTER COLUMN id SET DEFAULT nextval('orders_id_seq'::regclass);

ALTER TABLE ONLY product_order ALTER COLUMN id SET DEFAULT nextval('product_order_id_seq'::regclass);

ALTER TABLE ONLY products ALTER COLUMN pr_id SET DEFAULT nextval('products_id_seq'::regclass);

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);

INSERT INTO orders (id, date, discount, shipping_address, user_id)
VALUES (1, '2023-11-09', 0.01, 'address1', 2);
INSERT INTO orders (id, date, discount, shipping_address, user_id)
VALUES (3, '2023-08-02', 0.015, 'address3', 2);
INSERT INTO orders (id, date, discount, shipping_address, user_id)
VALUES (2, '2023-11-11', 0.02, 'address2', 1);

INSERT INTO product_order (id, product_id, order_id)
VALUES (1, 2, 1);
INSERT INTO product_order (id, product_id, order_id)
VALUES (2, 3, 1);
INSERT INTO product_order (id, product_id, order_id)
VALUES (3, 1, 2);
INSERT INTO product_order (id, product_id, order_id)
VALUES (4, 1, 3);
INSERT INTO product_order (id, product_id, order_id)
VALUES (5, 2, 3);
INSERT INTO product_order (id, product_id, order_id)
VALUES (6, 3, 3);

INSERT INTO products (pr_id, name, description, price)
VALUES (1, 'product1', 'description1', 200);
INSERT INTO products (pr_id, name, description, price)
VALUES (2, 'product2', 'description2', 300);
INSERT INTO products (pr_id, name, description, price)
VALUES (3, 'product3', 'description3', 400);
INSERT INTO products (pr_id, name, description, price)
VALUES (4, 'product4', 'description4', 400);

INSERT INTO users (id, last_name, first_name, email, password)
VALUES (3, 'LN3', 'FN3', 'email3', 'pass3');
INSERT INTO users (id, last_name, first_name, email, password)
VALUES (2, 'LN2', 'FN2', 'email2', 'pass2');
INSERT INTO users (id, last_name, first_name, email, password)
VALUES (1, 'LN1', 'FN1', 'email1', 'pass1');


SELECT pg_catalog.setval('orders_id_seq', 6, true);

SELECT pg_catalog.setval('product_order_id_seq', 13, true);

SELECT pg_catalog.setval('products_id_seq', 4, true);

SELECT pg_catalog.setval('users_id_seq', 41, true);

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);

ALTER TABLE ONLY product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (id);

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (pr_id);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY product_order
    ADD CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE ONLY product_order
    ADD CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES products(pr_id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(id) NOT VALID;


