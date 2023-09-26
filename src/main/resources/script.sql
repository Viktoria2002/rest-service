-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-09-25 15:20:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 830249)
-- Name: shop; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA shop;


ALTER SCHEMA shop OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 830256)
-- Name: orders; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.orders (
    id bigint NOT NULL,
    date date,
    discount numeric,
    shipping_address character varying,
    user_id bigint
);


ALTER TABLE shop.orders OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 830277)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

CREATE SEQUENCE shop.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop.orders_id_seq OWNER TO postgres;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 220
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: shop; Owner: postgres
--

ALTER SEQUENCE shop.orders_id_seq OWNED BY shop.orders.id;


--
-- TOC entry 222 (class 1259 OID 830310)
-- Name: product_order; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.product_order (
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    order_id bigint NOT NULL
);


ALTER TABLE shop.product_order OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 830309)
-- Name: product_order_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

CREATE SEQUENCE shop.product_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop.product_order_id_seq OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_order_id_seq; Type: SEQUENCE OWNED BY; Schema: shop; Owner: postgres
--

ALTER SEQUENCE shop.product_order_id_seq OWNED BY shop.product_order.id;


--
-- TOC entry 216 (class 1259 OID 830253)
-- Name: products; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.products (
    pr_id bigint NOT NULL,
    name character varying,
    description character varying,
    price numeric
);


ALTER TABLE shop.products OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 830268)
-- Name: products_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

CREATE SEQUENCE shop.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop.products_id_seq OWNER TO postgres;

--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 219
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: shop; Owner: postgres
--

ALTER SEQUENCE shop.products_id_seq OWNED BY shop.products.pr_id;


--
-- TOC entry 215 (class 1259 OID 830250)
-- Name: users; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.users (
    id bigint NOT NULL,
    last_name character varying,
    first_name character varying,
    email character varying,
    password character varying
);


ALTER TABLE shop.users OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 830259)
-- Name: users_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

CREATE SEQUENCE shop.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop.users_id_seq OWNER TO postgres;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: shop; Owner: postgres
--

ALTER SEQUENCE shop.users_id_seq OWNED BY shop.users.id;


--
-- TOC entry 3191 (class 2604 OID 830278)
-- Name: orders id; Type: DEFAULT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.orders ALTER COLUMN id SET DEFAULT nextval('shop.orders_id_seq'::regclass);


--
-- TOC entry 3192 (class 2604 OID 830313)
-- Name: product_order id; Type: DEFAULT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.product_order ALTER COLUMN id SET DEFAULT nextval('shop.product_order_id_seq'::regclass);


--
-- TOC entry 3190 (class 2604 OID 830269)
-- Name: products pr_id; Type: DEFAULT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.products ALTER COLUMN pr_id SET DEFAULT nextval('shop.products_id_seq'::regclass);


--
-- TOC entry 3189 (class 2604 OID 830260)
-- Name: users id; Type: DEFAULT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.users ALTER COLUMN id SET DEFAULT nextval('shop.users_id_seq'::regclass);


--
-- TOC entry 3348 (class 0 OID 830256)
-- Dependencies: 217
-- Data for Name: orders; Type: TABLE DATA; Schema: shop; Owner: postgres
--

INSERT INTO shop.orders (id, date, discount, shipping_address, user_id) VALUES (3, '2023-08-02', 0.015, 'address3', 2);
INSERT INTO shop.orders (id, date, discount, shipping_address, user_id) VALUES (2, '2023-11-11', 0.02, 'address2', 1);
INSERT INTO shop.orders (id, date, discount, shipping_address, user_id) VALUES (1, '2023-01-10', 0.2, 'address1', 2);


--
-- TOC entry 3353 (class 0 OID 830310)
-- Dependencies: 222
-- Data for Name: product_order; Type: TABLE DATA; Schema: shop; Owner: postgres
--

INSERT INTO shop.product_order (id, product_id, order_id) VALUES (3, 1, 2);
INSERT INTO shop.product_order (id, product_id, order_id) VALUES (4, 1, 3);
INSERT INTO shop.product_order (id, product_id, order_id) VALUES (5, 2, 3);
INSERT INTO shop.product_order (id, product_id, order_id) VALUES (6, 3, 3);
INSERT INTO shop.product_order (id, product_id, order_id) VALUES (1, 1, 1);


--
-- TOC entry 3347 (class 0 OID 830253)
-- Dependencies: 216
-- Data for Name: products; Type: TABLE DATA; Schema: shop; Owner: postgres
--

INSERT INTO shop.products (pr_id, name, description, price) VALUES (1, 'product1', 'description1', 200);
INSERT INTO shop.products (pr_id, name, description, price) VALUES (2, 'product2', 'description2', 300);
INSERT INTO shop.products (pr_id, name, description, price) VALUES (3, 'product3', 'description3', 400);


--
-- TOC entry 3346 (class 0 OID 830250)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: shop; Owner: postgres
--

INSERT INTO shop.users (id, last_name, first_name, email, password) VALUES (2, 'LN2', 'FN2', 'email2', 'pass2');
INSERT INTO shop.users (id, last_name, first_name, email, password) VALUES (1, 'LN1', 'FN1', 'email1', 'pass1');


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 220
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.orders_id_seq', 13, true);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_order_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.product_order_id_seq', 16, true);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 219
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.products_id_seq', 4, true);


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.users_id_seq', 44, true);


--
-- TOC entry 3198 (class 2606 OID 830285)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 830315)
-- Name: product_order product_order_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (id);


--
-- TOC entry 3196 (class 2606 OID 830276)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (pr_id);


--
-- TOC entry 3194 (class 2606 OID 830267)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 830321)
-- Name: product_order order_id; Type: FK CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.product_order
    ADD CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES shop.orders(id);


--
-- TOC entry 3203 (class 2606 OID 830316)
-- Name: product_order product_id; Type: FK CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.product_order
    ADD CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES shop.products(pr_id);


--
-- TOC entry 3201 (class 2606 OID 830286)
-- Name: orders user_id; Type: FK CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.orders
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES shop.users(id) NOT VALID;


-- Completed on 2023-09-25 15:20:56

--
-- PostgreSQL database dump complete
--

