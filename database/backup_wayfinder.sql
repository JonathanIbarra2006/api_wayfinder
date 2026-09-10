--
-- PostgreSQL database dump
--

\restrict pOoARHmxEysXV2WWfMHdFjZlp9jkWH6lOTBypa2Ai37F3gYXwCpERZBOOX7vGW2

-- Dumped from database version 16.15 (Ubuntu 16.15-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.15 (Ubuntu 16.15-0ubuntu0.24.04.1)

-- Started on 2026-09-09 21:51:03 -05

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4478 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 17466)
-- Name: categorias_vehiculos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorias_vehiculos (
    id_categoria integer NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion text
);


ALTER TABLE public.categorias_vehiculos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17465)
-- Name: categorias_vehiculos_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categorias_vehiculos_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categorias_vehiculos_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 4479 (class 0 OID 0)
-- Dependencies: 221
-- Name: categorias_vehiculos_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categorias_vehiculos_id_categoria_seq OWNED BY public.categorias_vehiculos.id_categoria;


--
-- TOC entry 230 (class 1259 OID 17523)
-- Name: poi_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.poi_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.poi_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17524)
-- Name: puntos_interes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.puntos_interes (
    id_poi integer DEFAULT nextval('public.poi_seq'::regclass) NOT NULL,
    id_tipo integer,
    nombre character varying(100) NOT NULL,
    id_ruta integer,
    coordenadas public.geometry(Point,4326)
);


ALTER TABLE public.puntos_interes OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17542)
-- Name: reportes_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reportes_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reportes_seq OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17543)
-- Name: reportes_comunidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reportes_comunidad (
    id_reporte integer DEFAULT nextval('public.reportes_seq'::regclass) NOT NULL,
    id_usuario integer,
    tipo_alerta character varying(50),
    coordenadas public.geometry(Point,4326),
    estado_activo boolean DEFAULT true,
    fecha_reporte timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.reportes_comunidad OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17514)
-- Name: rutas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rutas_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rutas_seq OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17515)
-- Name: rutas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rutas (
    id_ruta integer DEFAULT nextval('public.rutas_seq'::regclass) NOT NULL,
    nombre character varying(150) NOT NULL,
    descripcion text,
    dificultad character varying(20),
    distancia_km numeric(5,2),
    trazado public.geometry(LineString,4326)
);


ALTER TABLE public.rutas OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17509)
-- Name: tipos_poi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipos_poi (
    id_tipo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    icono_app character varying(50)
);


ALTER TABLE public.tipos_poi OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17475)
-- Name: usuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17476)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario integer DEFAULT nextval('public.usuarios_seq'::regclass) NOT NULL,
    nombre_completo character varying(100) NOT NULL,
    correo character varying(100) NOT NULL,
    contrasena_hash character varying(255),
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    password character varying(255),
    rol character varying(50) DEFAULT 'USER'::character varying
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17492)
-- Name: vehiculos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehiculos_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehiculos_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17493)
-- Name: vehiculos_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehiculos_usuario (
    id_vehiculo integer DEFAULT nextval('public.vehiculos_seq'::regclass) NOT NULL,
    id_usuario integer,
    id_categoria integer,
    marca character varying(50),
    cilindraje_cc integer,
    autonomia_km integer
);


ALTER TABLE public.vehiculos_usuario OWNER TO postgres;

--
-- TOC entry 4278 (class 2604 OID 17469)
-- Name: categorias_vehiculos id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias_vehiculos ALTER COLUMN id_categoria SET DEFAULT nextval('public.categorias_vehiculos_id_categoria_seq'::regclass);


--
-- TOC entry 4461 (class 0 OID 17466)
-- Dependencies: 222
-- Data for Name: categorias_vehiculos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorias_vehiculos (id_categoria, nombre, descripcion) FROM stdin;
1	Moto de Calle	\N
2	Moto Adventure / Off-Road	\N
3	Automóvil Estándar	\N
4	Vehículo 4x4	\N
\.


--
-- TOC entry 4470 (class 0 OID 17524)
-- Dependencies: 231
-- Data for Name: puntos_interes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.puntos_interes (id_poi, id_tipo, nombre, id_ruta, coordenadas) FROM stdin;
100	1	Gasolinera Terpel Vía El Zulia	100	0101000020E610000052B81E85EB2152C09A99999999991F40
\.


--
-- TOC entry 4472 (class 0 OID 17543)
-- Dependencies: 233
-- Data for Name: reportes_comunidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reportes_comunidad (id_reporte, id_usuario, tipo_alerta, coordenadas, estado_activo, fecha_reporte) FROM stdin;
\.


--
-- TOC entry 4468 (class 0 OID 17515)
-- Dependencies: 229
-- Data for Name: rutas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rutas (id_ruta, nombre, descripcion, dificultad, distancia_km, trazado) FROM stdin;
100	Cúcuta - El Zulia	Ruta corta, ideal para probar la moto después de un mantenimiento.	Fácil	12.50	0102000020E610000003000000B81E85EB512052C0DF4F8D976E921F4033333333332352C0A4703D0AD7A31F4066666666662652C0B81E85EB51B81F40
\.


--
-- TOC entry 4277 (class 0 OID 16707)
-- Dependencies: 217
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- TOC entry 4466 (class 0 OID 17509)
-- Dependencies: 227
-- Data for Name: tipos_poi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipos_poi (id_tipo, nombre, icono_app) FROM stdin;
1	Estación de Servicio	gas_station_icon
2	Restaurante / Parador	restaurant_icon
3	Mirador / Paisaje	camera_icon
4	Taller Mecánico	wrench_icon
\.


--
-- TOC entry 4463 (class 0 OID 17476)
-- Dependencies: 224
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id_usuario, nombre_completo, correo, contrasena_hash, fecha_registro, password, rol) FROM stdin;
103	Carlos Perez	carlos@gmail.com	\N	2026-05-23 19:32:07.579389	$2a$10$1i6/TJO3Nc0qQ6QrGJhnBOBSPHkMTp/odN2ht1DDN4UJg9Y/cXiHK	USER
100	Jonathan Ibarra	jonathan.admin@wayfinder.com	$2a$12$pRWzfgpeiiUGLog4xoV46.tSwdh.hCeimi7PB1sB.DB69ZVNnbOVS	2026-05-17 22:20:33.191334	$2a$12$pRWzfgpeiiUGLog4xoV46.tSwdh.hCeimi7PB1sB.DB69ZVNnbOVS	ADMIN
\.


--
-- TOC entry 4465 (class 0 OID 17493)
-- Dependencies: 226
-- Data for Name: vehiculos_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehiculos_usuario (id_vehiculo, id_usuario, id_categoria, marca, cilindraje_cc, autonomia_km) FROM stdin;
\.


--
-- TOC entry 4480 (class 0 OID 0)
-- Dependencies: 221
-- Name: categorias_vehiculos_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categorias_vehiculos_id_categoria_seq', 11, false);


--
-- TOC entry 4481 (class 0 OID 0)
-- Dependencies: 230
-- Name: poi_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.poi_seq', 100, true);


--
-- TOC entry 4482 (class 0 OID 0)
-- Dependencies: 232
-- Name: reportes_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reportes_seq', 100, false);


--
-- TOC entry 4483 (class 0 OID 0)
-- Dependencies: 228
-- Name: rutas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rutas_seq', 100, true);


--
-- TOC entry 4484 (class 0 OID 0)
-- Dependencies: 223
-- Name: usuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_seq', 103, true);


--
-- TOC entry 4485 (class 0 OID 0)
-- Dependencies: 225
-- Name: vehiculos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehiculos_seq', 100, false);


--
-- TOC entry 4292 (class 2606 OID 17473)
-- Name: categorias_vehiculos categorias_vehiculos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias_vehiculos
    ADD CONSTRAINT categorias_vehiculos_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 4304 (class 2606 OID 17531)
-- Name: puntos_interes puntos_interes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puntos_interes
    ADD CONSTRAINT puntos_interes_pkey PRIMARY KEY (id_poi);


--
-- TOC entry 4306 (class 2606 OID 17552)
-- Name: reportes_comunidad reportes_comunidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reportes_comunidad
    ADD CONSTRAINT reportes_comunidad_pkey PRIMARY KEY (id_reporte);


--
-- TOC entry 4302 (class 2606 OID 17522)
-- Name: rutas rutas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rutas
    ADD CONSTRAINT rutas_pkey PRIMARY KEY (id_ruta);


--
-- TOC entry 4300 (class 2606 OID 17513)
-- Name: tipos_poi tipos_poi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipos_poi
    ADD CONSTRAINT tipos_poi_pkey PRIMARY KEY (id_tipo);


--
-- TOC entry 4294 (class 2606 OID 17484)
-- Name: usuarios usuarios_correo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_correo_key UNIQUE (correo);


--
-- TOC entry 4296 (class 2606 OID 17482)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 4298 (class 2606 OID 17498)
-- Name: vehiculos_usuario vehiculos_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehiculos_usuario
    ADD CONSTRAINT vehiculos_usuario_pkey PRIMARY KEY (id_vehiculo);


--
-- TOC entry 4309 (class 2606 OID 17537)
-- Name: puntos_interes puntos_interes_id_ruta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puntos_interes
    ADD CONSTRAINT puntos_interes_id_ruta_fkey FOREIGN KEY (id_ruta) REFERENCES public.rutas(id_ruta);


--
-- TOC entry 4310 (class 2606 OID 17532)
-- Name: puntos_interes puntos_interes_id_tipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puntos_interes
    ADD CONSTRAINT puntos_interes_id_tipo_fkey FOREIGN KEY (id_tipo) REFERENCES public.tipos_poi(id_tipo);


--
-- TOC entry 4311 (class 2606 OID 17553)
-- Name: reportes_comunidad reportes_comunidad_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reportes_comunidad
    ADD CONSTRAINT reportes_comunidad_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario);


--
-- TOC entry 4307 (class 2606 OID 17504)
-- Name: vehiculos_usuario vehiculos_usuario_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehiculos_usuario
    ADD CONSTRAINT vehiculos_usuario_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES public.categorias_vehiculos(id_categoria);


--
-- TOC entry 4308 (class 2606 OID 17499)
-- Name: vehiculos_usuario vehiculos_usuario_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehiculos_usuario
    ADD CONSTRAINT vehiculos_usuario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


-- Completed on 2026-09-09 21:51:03 -05

--
-- PostgreSQL database dump complete
--

\unrestrict pOoARHmxEysXV2WWfMHdFjZlp9jkWH6lOTBypa2Ai37F3gYXwCpERZBOOX7vGW2

