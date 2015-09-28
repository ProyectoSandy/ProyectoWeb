--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-09-27 23:08:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 191 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 191
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 17136)
-- Name: actividades; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actividades (
    codactividad integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text NOT NULL,
    responsable character varying(100) NOT NULL,
    valoracion integer,
    coddocente integer,
    codtipo integer
);


ALTER TABLE actividades OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 17134)
-- Name: actividades_codactividades_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE actividades_codactividades_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actividades_codactividades_seq OWNER TO postgres;

--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 175
-- Name: actividades_codactividades_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE actividades_codactividades_seq OWNED BY actividades.codactividad;


--
-- TOC entry 188 (class 1259 OID 17225)
-- Name: clases; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clases (
    codclase integer NOT NULL,
    dia integer NOT NULL,
    nombre character varying(100) NOT NULL,
    codjornada integer,
    coddocente integer
);


ALTER TABLE clases OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 17223)
-- Name: clases_codclase_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clases_codclase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clases_codclase_seq OWNER TO postgres;

--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 187
-- Name: clases_codclase_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clases_codclase_seq OWNED BY clases.codclase;


--
-- TOC entry 190 (class 1259 OID 17243)
-- Name: convenciones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE convenciones (
    codconvencion integer NOT NULL,
    nombre character varying(50) NOT NULL,
    color character varying(45) NOT NULL,
    codclase integer
);


ALTER TABLE convenciones OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17241)
-- Name: convencion_codconvencion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE convencion_codconvencion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE convencion_codconvencion_seq OWNER TO postgres;

--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 189
-- Name: convencion_codconvencion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE convencion_codconvencion_seq OWNED BY convenciones.codconvencion;


--
-- TOC entry 172 (class 1259 OID 17118)
-- Name: docentes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE docentes (
    cedula integer NOT NULL,
    apellidos character varying(100) NOT NULL,
    nombres character varying(100) NOT NULL,
    codigo character varying(50),
    facultad integer,
    unidadacademica integer,
    semestre integer NOT NULL,
    formacion text,
    direccion character varying(100),
    telefono character varying(50),
    correo character varying(100)
);


ALTER TABLE docentes OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 17186)
-- Name: evaluaciones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluaciones (
    codeva integer NOT NULL,
    coddoc integer,
    codprod integer,
    calificacion integer NOT NULL
);


ALTER TABLE evaluaciones OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 17184)
-- Name: evaluacion_codeva_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE evaluacion_codeva_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE evaluacion_codeva_seq OWNER TO postgres;

--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 181
-- Name: evaluacion_codeva_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE evaluacion_codeva_seq OWNED BY evaluaciones.codeva;


--
-- TOC entry 178 (class 1259 OID 17157)
-- Name: fechaseguimientos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fechaseguimientos (
    codfechseg integer NOT NULL,
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    codact integer
);


ALTER TABLE fechaseguimientos OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 17155)
-- Name: fechaseguimiento_codfechseg_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fechaseguimiento_codfechseg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fechaseguimiento_codfechseg_seq OWNER TO postgres;

--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 177
-- Name: fechaseguimiento_codfechseg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fechaseguimiento_codfechseg_seq OWNED BY fechaseguimientos.codfechseg;


--
-- TOC entry 184 (class 1259 OID 17204)
-- Name: horas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE horas (
    codhora integer NOT NULL,
    hora character varying(45) NOT NULL
);


ALTER TABLE horas OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 17202)
-- Name: horas_codhora_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE horas_codhora_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE horas_codhora_seq OWNER TO postgres;

--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 183
-- Name: horas_codhora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE horas_codhora_seq OWNED BY horas.codhora;


--
-- TOC entry 186 (class 1259 OID 17212)
-- Name: jornadas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jornadas (
    codjornada integer NOT NULL,
    nombre character varying(100) NOT NULL,
    codhora integer
);


ALTER TABLE jornadas OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 17210)
-- Name: jornada_codjornada_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jornada_codjornada_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jornada_codjornada_seq OWNER TO postgres;

--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 185
-- Name: jornada_codjornada_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jornada_codjornada_seq OWNED BY jornadas.codjornada;


--
-- TOC entry 180 (class 1259 OID 17170)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE productos (
    codproducto integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fechacompromiso date NOT NULL,
    fechaentrega date NOT NULL,
    comentarios text,
    codact integer
);


ALTER TABLE productos OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 17168)
-- Name: productos_codproducto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE productos_codproducto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productos_codproducto_seq OWNER TO postgres;

--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 179
-- Name: productos_codproducto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE productos_codproducto_seq OWNED BY productos.codproducto;


--
-- TOC entry 174 (class 1259 OID 17128)
-- Name: tipomodalidades; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipomodalidades (
    codtipo integer NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE tipomodalidades OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 17126)
-- Name: tipomodalidad_codtipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipomodalidad_codtipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipomodalidad_codtipo_seq OWNER TO postgres;

--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 173
-- Name: tipomodalidad_codtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipomodalidad_codtipo_seq OWNED BY tipomodalidades.codtipo;


--
-- TOC entry 1937 (class 2604 OID 17139)
-- Name: codactividad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades ALTER COLUMN codactividad SET DEFAULT nextval('actividades_codactividades_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 17228)
-- Name: codclase; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases ALTER COLUMN codclase SET DEFAULT nextval('clases_codclase_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 17246)
-- Name: codconvencion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convenciones ALTER COLUMN codconvencion SET DEFAULT nextval('convencion_codconvencion_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 17189)
-- Name: codeva; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluaciones ALTER COLUMN codeva SET DEFAULT nextval('evaluacion_codeva_seq'::regclass);


--
-- TOC entry 1938 (class 2604 OID 17160)
-- Name: codfechseg; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fechaseguimientos ALTER COLUMN codfechseg SET DEFAULT nextval('fechaseguimiento_codfechseg_seq'::regclass);


--
-- TOC entry 1941 (class 2604 OID 17207)
-- Name: codhora; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horas ALTER COLUMN codhora SET DEFAULT nextval('horas_codhora_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 17215)
-- Name: codjornada; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jornadas ALTER COLUMN codjornada SET DEFAULT nextval('jornada_codjornada_seq'::regclass);


--
-- TOC entry 1939 (class 2604 OID 17173)
-- Name: codproducto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos ALTER COLUMN codproducto SET DEFAULT nextval('productos_codproducto_seq'::regclass);


--
-- TOC entry 1936 (class 2604 OID 17131)
-- Name: codtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomodalidades ALTER COLUMN codtipo SET DEFAULT nextval('tipomodalidad_codtipo_seq'::regclass);


--
-- TOC entry 2089 (class 0 OID 17136)
-- Dependencies: 176
-- Data for Name: actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actividades (codactividad, nombre, descripcion, responsable, valoracion, coddocente, codtipo) FROM stdin;
2	actividad2 modificada	bla bla bla bla	xxxxxxxx	1	34	2
\.


--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 175
-- Name: actividades_codactividades_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('actividades_codactividades_seq', 2, true);


--
-- TOC entry 2101 (class 0 OID 17225)
-- Dependencies: 188
-- Data for Name: clases; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY clases (codclase, dia, nombre, codjornada, coddocente) FROM stdin;
\.


--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 187
-- Name: clases_codclase_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('clases_codclase_seq', 1, false);


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 189
-- Name: convencion_codconvencion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('convencion_codconvencion_seq', 1, false);


--
-- TOC entry 2103 (class 0 OID 17243)
-- Dependencies: 190
-- Data for Name: convenciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY convenciones (codconvencion, nombre, color, codclase) FROM stdin;
\.


--
-- TOC entry 2085 (class 0 OID 17118)
-- Dependencies: 172
-- Data for Name: docentes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY docentes (cedula, apellidos, nombres, codigo, facultad, unidadacademica, semestre, formacion, direccion, telefono, correo) FROM stdin;
12	xfdxf	aeawe	xcvx	1	1	1	dsad	dgdfg	ghj	ghjt
34	hjkjhk	elkin	yu	23	3	4	hjkdgdfgdg	dfyj	kiu	nvb
\.


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 181
-- Name: evaluacion_codeva_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('evaluacion_codeva_seq', 1, false);


--
-- TOC entry 2095 (class 0 OID 17186)
-- Dependencies: 182
-- Data for Name: evaluaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY evaluaciones (codeva, coddoc, codprod, calificacion) FROM stdin;
\.


--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 177
-- Name: fechaseguimiento_codfechseg_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fechaseguimiento_codfechseg_seq', 1, false);


--
-- TOC entry 2091 (class 0 OID 17157)
-- Dependencies: 178
-- Data for Name: fechaseguimientos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fechaseguimientos (codfechseg, fechainicio, fechafin, codact) FROM stdin;
\.


--
-- TOC entry 2097 (class 0 OID 17204)
-- Dependencies: 184
-- Data for Name: horas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY horas (codhora, hora) FROM stdin;
2	45
\.


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 183
-- Name: horas_codhora_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('horas_codhora_seq', 2, true);


--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 185
-- Name: jornada_codjornada_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('jornada_codjornada_seq', 1, false);


--
-- TOC entry 2099 (class 0 OID 17212)
-- Dependencies: 186
-- Data for Name: jornadas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jornadas (codjornada, nombre, codhora) FROM stdin;
\.


--
-- TOC entry 2093 (class 0 OID 17170)
-- Dependencies: 180
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY productos (codproducto, descripcion, fechacompromiso, fechaentrega, comentarios, codact) FROM stdin;
2	nueva descripcion	2015-09-09	2015-09-22	otro comentario	2
\.


--
-- TOC entry 2128 (class 0 OID 0)
-- Dependencies: 179
-- Name: productos_codproducto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('productos_codproducto_seq', 2, true);


--
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 173
-- Name: tipomodalidad_codtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipomodalidad_codtipo_seq', 2, true);


--
-- TOC entry 2087 (class 0 OID 17128)
-- Dependencies: 174
-- Data for Name: tipomodalidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipomodalidades (codtipo, nombre) FROM stdin;
2	segundo_tipo_editado
\.


--
-- TOC entry 1948 (class 2606 OID 17133)
-- Name: pf_tipo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipomodalidades
    ADD CONSTRAINT pf_tipo PRIMARY KEY (codtipo);


--
-- TOC entry 1950 (class 2606 OID 17144)
-- Name: pk_act; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT pk_act PRIMARY KEY (codactividad);


--
-- TOC entry 1963 (class 2606 OID 17230)
-- Name: pk_clase; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT pk_clase PRIMARY KEY (codclase);


--
-- TOC entry 1953 (class 2606 OID 17162)
-- Name: pk_codfe; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fechaseguimientos
    ADD CONSTRAINT pk_codfe PRIMARY KEY (codfechseg);


--
-- TOC entry 1965 (class 2606 OID 17248)
-- Name: pk_conven; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY convenciones
    ADD CONSTRAINT pk_conven PRIMARY KEY (codconvencion);


--
-- TOC entry 1946 (class 2606 OID 17125)
-- Name: pk_docentes; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY docentes
    ADD CONSTRAINT pk_docentes PRIMARY KEY (cedula);


--
-- TOC entry 1957 (class 2606 OID 17191)
-- Name: pk_eva; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluaciones
    ADD CONSTRAINT pk_eva PRIMARY KEY (codeva);


--
-- TOC entry 1959 (class 2606 OID 17209)
-- Name: pk_hora; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY horas
    ADD CONSTRAINT pk_hora PRIMARY KEY (codhora);


--
-- TOC entry 1961 (class 2606 OID 17217)
-- Name: pk_jornada; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jornadas
    ADD CONSTRAINT pk_jornada PRIMARY KEY (codjornada);


--
-- TOC entry 1955 (class 2606 OID 17178)
-- Name: pk_prod; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT pk_prod PRIMARY KEY (codproducto);


--
-- TOC entry 1951 (class 1259 OID 17260)
-- Name: fki_fechasseg_actividad; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_fechasseg_actividad ON fechaseguimientos USING btree (codact);


--
-- TOC entry 1966 (class 2606 OID 17145)
-- Name: actividades_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula) ON DELETE CASCADE;


--
-- TOC entry 1967 (class 2606 OID 17150)
-- Name: actividades_codtipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_codtipo_fkey FOREIGN KEY (codtipo) REFERENCES tipomodalidades(codtipo) ON DELETE CASCADE;


--
-- TOC entry 1974 (class 2606 OID 17236)
-- Name: clases_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT clases_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);


--
-- TOC entry 1973 (class 2606 OID 17231)
-- Name: clases_codjornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT clases_codjornada_fkey FOREIGN KEY (codjornada) REFERENCES jornadas(codjornada);


--
-- TOC entry 1975 (class 2606 OID 17249)
-- Name: convencion_codclase_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convenciones
    ADD CONSTRAINT convencion_codclase_fkey FOREIGN KEY (codclase) REFERENCES clases(codclase);


--
-- TOC entry 1970 (class 2606 OID 17192)
-- Name: evaluacion_coddoc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluaciones
    ADD CONSTRAINT evaluacion_coddoc_fkey FOREIGN KEY (coddoc) REFERENCES docentes(cedula);


--
-- TOC entry 1971 (class 2606 OID 17197)
-- Name: evaluacion_codprod_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluaciones
    ADD CONSTRAINT evaluacion_codprod_fkey FOREIGN KEY (codprod) REFERENCES productos(codproducto);


--
-- TOC entry 1968 (class 2606 OID 17255)
-- Name: fk_fechasseg_actividad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fechaseguimientos
    ADD CONSTRAINT fk_fechasseg_actividad FOREIGN KEY (codact) REFERENCES actividades(codactividad);


--
-- TOC entry 1972 (class 2606 OID 17218)
-- Name: jornada_codhora_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jornadas
    ADD CONSTRAINT jornada_codhora_fkey FOREIGN KEY (codhora) REFERENCES horas(codhora) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1969 (class 2606 OID 17179)
-- Name: productos_codact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_codact_fkey FOREIGN KEY (codact) REFERENCES actividades(codactividad) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-09-27 23:08:58

--
-- PostgreSQL database dump complete
--

