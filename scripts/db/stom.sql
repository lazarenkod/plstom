--
-- PostgreSQL database dump
--

-- Started on 2008-09-25 18:37:19

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1819 (class 1262 OID 16404)
-- Name: stomatology; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE stomatology WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE stomatology OWNER TO postgres;

\connect stomatology

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 335 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1507 (class 1259 OID 16497)
-- Dependencies: 6
-- Name: pl_cards; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_cards (
    id integer NOT NULL,
    id_percents integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.pl_cards OWNER TO postgres;

--
-- TOC entry 1509 (class 1259 OID 16507)
-- Dependencies: 6
-- Name: pl_peoples; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_peoples (
    id integer NOT NULL,
    name character varying(32) NOT NULL,
    sname character varying(32) NOT NULL,
    lname character varying(64) NOT NULL,
    birthdate date NOT NULL,
    dul_id integer NOT NULL,
    address character varying(100)
);


ALTER TABLE public.pl_peoples OWNER TO postgres;

--
-- TOC entry 1822 (class 0 OID 0)
-- Dependencies: 1509
-- Name: COLUMN pl_peoples.dul_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pl_peoples.dul_id IS 'Ссылка на ДУЛ';


--
-- TOC entry 1510 (class 1259 OID 16510)
-- Dependencies: 1509 6
-- Name: pl_clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_clients_id_seq OWNER TO postgres;

--
-- TOC entry 1823 (class 0 OID 0)
-- Dependencies: 1510
-- Name: pl_clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_clients_id_seq OWNED BY pl_peoples.id;


--
-- TOC entry 1511 (class 1259 OID 16512)
-- Dependencies: 1804 1509 6
-- Name: pl_client; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_client (
    phone character varying(11),
    mobile character varying(11),
    add_id integer
)
INHERITS (pl_peoples);


ALTER TABLE public.pl_client OWNER TO postgres;

--
-- TOC entry 1824 (class 0 OID 0)
-- Dependencies: 1511
-- Name: COLUMN pl_client.add_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pl_client.add_id IS 'Дополнительный id для поиска карточек';


--
-- TOC entry 1512 (class 1259 OID 16515)
-- Dependencies: 6
-- Name: pl_client_to_doctor_visits_doc; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_client_to_doctor_visits_doc (
    id integer NOT NULL,
    id_doctor integer NOT NULL,
    id_client integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.pl_client_to_doctor_visits_doc OWNER TO postgres;

--
-- TOC entry 1825 (class 0 OID 0)
-- Dependencies: 1512
-- Name: COLUMN pl_client_to_doctor_visits_doc.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pl_client_to_doctor_visits_doc.id IS '
';


--
-- TOC entry 1514 (class 1259 OID 16520)
-- Dependencies: 6
-- Name: pl_contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_contacts (
    id integer NOT NULL,
    id_type integer NOT NULL,
    value character varying(512) NOT NULL,
    id_people integer NOT NULL
);


ALTER TABLE public.pl_contacts OWNER TO postgres;

--
-- TOC entry 1516 (class 1259 OID 16528)
-- Dependencies: 1807 1509 6
-- Name: pl_doctors; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_doctors (
    pl_post integer NOT NULL
)
INHERITS (pl_peoples);


ALTER TABLE public.pl_doctors OWNER TO postgres;

--
-- TOC entry 1517 (class 1259 OID 16531)
-- Dependencies: 6
-- Name: pl_dul; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_dul (
    id integer NOT NULL,
    num character varying(20) NOT NULL,
    issue_date date NOT NULL,
    issuer_division character varying(20),
    issuer_name character varying(512),
    series character varying(20) NOT NULL,
    type character varying(30)
);


ALTER TABLE public.pl_dul OWNER TO postgres;

--
-- TOC entry 1519 (class 1259 OID 16539)
-- Dependencies: 6
-- Name: pl_metrologies; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_metrologies (
    id integer NOT NULL,
    name character varying(32) NOT NULL,
    short_name character varying(10) NOT NULL
);


ALTER TABLE public.pl_metrologies OWNER TO postgres;

--
-- TOC entry 1533 (class 1259 OID 24587)
-- Dependencies: 6
-- Name: pl_norms; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_norms (
    id integer NOT NULL,
    id_resource integer NOT NULL,
    id_service integer NOT NULL,
    amount real NOT NULL
);


ALTER TABLE public.pl_norms OWNER TO postgres;

--
-- TOC entry 1521 (class 1259 OID 16544)
-- Dependencies: 6
-- Name: pl_object_types; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_object_types (
    id integer NOT NULL,
    id_parent integer,
    name character varying(32)
);


ALTER TABLE public.pl_object_types OWNER TO postgres;

--
-- TOC entry 1523 (class 1259 OID 16549)
-- Dependencies: 6
-- Name: pl_payments; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_payments (
    id integer NOT NULL,
    summ real NOT NULL,
    id_visit integer NOT NULL,
    date date NOT NULL,
    id_card integer NOT NULL
);


ALTER TABLE public.pl_payments OWNER TO postgres;

--
-- TOC entry 1525 (class 1259 OID 16554)
-- Dependencies: 6
-- Name: pl_posts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_posts (
    id integer NOT NULL,
    name character varying(128) NOT NULL
);


ALTER TABLE public.pl_posts OWNER TO postgres;

--
-- TOC entry 1527 (class 1259 OID 16559)
-- Dependencies: 6
-- Name: pl_resources; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_resources (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    id_metrology integer NOT NULL
);


ALTER TABLE public.pl_resources OWNER TO postgres;

--
-- TOC entry 1529 (class 1259 OID 16564)
-- Dependencies: 6
-- Name: pl_resources_store; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_resources_store (
    id integer NOT NULL,
    id_resource integer NOT NULL,
    id_store integer NOT NULL,
    count real NOT NULL
);


ALTER TABLE public.pl_resources_store OWNER TO postgres;

--
-- TOC entry 1531 (class 1259 OID 16569)
-- Dependencies: 6
-- Name: pl_services; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pl_services (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    cost real NOT NULL
);


ALTER TABLE public.pl_services OWNER TO postgres;

--
-- TOC entry 1508 (class 1259 OID 16500)
-- Dependencies: 6 1507
-- Name: pl_cards_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_cards_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_cards_id_seq OWNER TO postgres;

--
-- TOC entry 1826 (class 0 OID 0)
-- Dependencies: 1508
-- Name: pl_cards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_cards_id_seq OWNED BY pl_cards.id;


--
-- TOC entry 1513 (class 1259 OID 16518)
-- Dependencies: 1512 6
-- Name: pl_client_visits_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_client_visits_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_client_visits_id_seq OWNER TO postgres;

--
-- TOC entry 1827 (class 0 OID 0)
-- Dependencies: 1513
-- Name: pl_client_visits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_client_visits_id_seq OWNED BY pl_client_to_doctor_visits_doc.id;


--
-- TOC entry 1515 (class 1259 OID 16526)
-- Dependencies: 6 1514
-- Name: pl_contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_contacts_id_seq OWNER TO postgres;

--
-- TOC entry 1828 (class 0 OID 0)
-- Dependencies: 1515
-- Name: pl_contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_contacts_id_seq OWNED BY pl_contacts.id;


--
-- TOC entry 1518 (class 1259 OID 16537)
-- Dependencies: 6 1517
-- Name: pl_dul_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_dul_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_dul_id_seq OWNER TO postgres;

--
-- TOC entry 1829 (class 0 OID 0)
-- Dependencies: 1518
-- Name: pl_dul_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_dul_id_seq OWNED BY pl_dul.id;


--
-- TOC entry 1520 (class 1259 OID 16542)
-- Dependencies: 6 1519
-- Name: pl_metrologies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_metrologies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_metrologies_id_seq OWNER TO postgres;

--
-- TOC entry 1830 (class 0 OID 0)
-- Dependencies: 1520
-- Name: pl_metrologies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_metrologies_id_seq OWNED BY pl_metrologies.id;


--
-- TOC entry 1535 (class 1259 OID 24592)
-- Dependencies: 1533 6
-- Name: pl_norms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_norms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_norms_id_seq OWNER TO postgres;

--
-- TOC entry 1831 (class 0 OID 0)
-- Dependencies: 1535
-- Name: pl_norms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_norms_id_seq OWNED BY pl_norms.id;


--
-- TOC entry 1534 (class 1259 OID 24590)
-- Dependencies: 6
-- Name: pl_norms_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_norms_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_norms_seq OWNER TO postgres;

--
-- TOC entry 1522 (class 1259 OID 16547)
-- Dependencies: 6 1521
-- Name: pl_object_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_object_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_object_types_id_seq OWNER TO postgres;

--
-- TOC entry 1832 (class 0 OID 0)
-- Dependencies: 1522
-- Name: pl_object_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_object_types_id_seq OWNED BY pl_object_types.id;


--
-- TOC entry 1524 (class 1259 OID 16552)
-- Dependencies: 6 1523
-- Name: pl_payments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_payments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_payments_id_seq OWNER TO postgres;

--
-- TOC entry 1833 (class 0 OID 0)
-- Dependencies: 1524
-- Name: pl_payments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_payments_id_seq OWNED BY pl_payments.id;


--
-- TOC entry 1526 (class 1259 OID 16557)
-- Dependencies: 6 1525
-- Name: pl_posts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_posts_id_seq OWNER TO postgres;

--
-- TOC entry 1834 (class 0 OID 0)
-- Dependencies: 1526
-- Name: pl_posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_posts_id_seq OWNED BY pl_posts.id;


--
-- TOC entry 1528 (class 1259 OID 16562)
-- Dependencies: 1527 6
-- Name: pl_resources_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_resources_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_resources_id_seq OWNER TO postgres;

--
-- TOC entry 1835 (class 0 OID 0)
-- Dependencies: 1528
-- Name: pl_resources_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_resources_id_seq OWNED BY pl_resources.id;


--
-- TOC entry 1530 (class 1259 OID 16567)
-- Dependencies: 1529 6
-- Name: pl_resources_store_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_resources_store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_resources_store_id_seq OWNER TO postgres;

--
-- TOC entry 1836 (class 0 OID 0)
-- Dependencies: 1530
-- Name: pl_resources_store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_resources_store_id_seq OWNED BY pl_resources_store.id;


--
-- TOC entry 1532 (class 1259 OID 16572)
-- Dependencies: 6 1531
-- Name: pl_services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pl_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pl_services_id_seq OWNER TO postgres;

--
-- TOC entry 1837 (class 0 OID 0)
-- Dependencies: 1532
-- Name: pl_services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pl_services_id_seq OWNED BY pl_services.id;


--
-- TOC entry 1802 (class 2604 OID 16574)
-- Dependencies: 1508 1507
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_cards ALTER COLUMN id SET DEFAULT nextval('pl_cards_id_seq'::regclass);


--
-- TOC entry 1805 (class 2604 OID 16576)
-- Dependencies: 1513 1512
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_client_to_doctor_visits_doc ALTER COLUMN id SET DEFAULT nextval('pl_client_visits_id_seq'::regclass);


--
-- TOC entry 1806 (class 2604 OID 16577)
-- Dependencies: 1515 1514
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_contacts ALTER COLUMN id SET DEFAULT nextval('pl_contacts_id_seq'::regclass);


--
-- TOC entry 1808 (class 2604 OID 16578)
-- Dependencies: 1518 1517
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_dul ALTER COLUMN id SET DEFAULT nextval('pl_dul_id_seq'::regclass);


--
-- TOC entry 1809 (class 2604 OID 16579)
-- Dependencies: 1520 1519
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_metrologies ALTER COLUMN id SET DEFAULT nextval('pl_metrologies_id_seq'::regclass);


--
-- TOC entry 1816 (class 2604 OID 24594)
-- Dependencies: 1535 1533
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_norms ALTER COLUMN id SET DEFAULT nextval('pl_norms_id_seq'::regclass);


--
-- TOC entry 1810 (class 2604 OID 16580)
-- Dependencies: 1522 1521
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_object_types ALTER COLUMN id SET DEFAULT nextval('pl_object_types_id_seq'::regclass);


--
-- TOC entry 1811 (class 2604 OID 16581)
-- Dependencies: 1524 1523
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_payments ALTER COLUMN id SET DEFAULT nextval('pl_payments_id_seq'::regclass);


--
-- TOC entry 1803 (class 2604 OID 16582)
-- Dependencies: 1510 1509
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_peoples ALTER COLUMN id SET DEFAULT nextval('pl_clients_id_seq'::regclass);


--
-- TOC entry 1812 (class 2604 OID 16585)
-- Dependencies: 1526 1525
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_posts ALTER COLUMN id SET DEFAULT nextval('pl_posts_id_seq'::regclass);


--
-- TOC entry 1813 (class 2604 OID 16586)
-- Dependencies: 1528 1527
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_resources ALTER COLUMN id SET DEFAULT nextval('pl_resources_id_seq'::regclass);


--
-- TOC entry 1814 (class 2604 OID 16587)
-- Dependencies: 1530 1529
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_resources_store ALTER COLUMN id SET DEFAULT nextval('pl_resources_store_id_seq'::regclass);


--
-- TOC entry 1815 (class 2604 OID 16588)
-- Dependencies: 1532 1531
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE pl_services ALTER COLUMN id SET DEFAULT nextval('pl_services_id_seq'::regclass);


--
-- TOC entry 1821 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2008-09-25 18:37:20

--
-- PostgreSQL database dump complete
--

