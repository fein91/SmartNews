--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-08-18 09:16:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 181 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 16531)
-- Name: article; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE article (
    id bigint DEFAULT nextval('article_seq'::regclass) NOT NULL,
    name character varying(4000),
    folder_fk bigint,
    url character varying(4000),
    description character varying(4000)
);


--
-- TOC entry 179 (class 1259 OID 16599)
-- Name: article_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE article_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 173 (class 1259 OID 16537)
-- Name: article_tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE article_tag (
    article_fk bigint NOT NULL,
    tag_fk bigint NOT NULL
);


--
-- TOC entry 174 (class 1259 OID 16540)
-- Name: client_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 175 (class 1259 OID 16542)
-- Name: client; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE client (
    id bigint DEFAULT nextval('client_seq'::regclass) NOT NULL,
    name character varying
);


--
-- TOC entry 176 (class 1259 OID 16549)
-- Name: folder_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE folder_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 177 (class 1259 OID 16551)
-- Name: folder; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE folder (
    id bigint DEFAULT nextval('folder_seq'::regclass) NOT NULL,
    parent_fk bigint,
    client_fk bigint,
    name character varying
);


--
-- TOC entry 178 (class 1259 OID 16558)
-- Name: tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tag (
    id bigint NOT NULL,
    name character varying
);


--
-- TOC entry 180 (class 1259 OID 16601)
-- Name: tag_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tag_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1910 (class 2606 OID 16565)
-- Name: article_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 1912 (class 2606 OID 16567)
-- Name: article_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article_tag
    ADD CONSTRAINT article_tag_pkey PRIMARY KEY (article_fk, tag_fk);


--
-- TOC entry 1916 (class 2606 OID 16569)
-- Name: folder_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY folder
    ADD CONSTRAINT folder_pkey PRIMARY KEY (id);


--
-- TOC entry 1918 (class 2606 OID 16571)
-- Name: tag_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- TOC entry 1914 (class 2606 OID 16573)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY client
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 1919 (class 2606 OID 16574)
-- Name: article_folderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT "article_folderId_fkey" FOREIGN KEY (folder_fk) REFERENCES folder(id);


--
-- TOC entry 1920 (class 2606 OID 16579)
-- Name: article_tag_articleId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article_tag
    ADD CONSTRAINT "article_tag_articleId_fkey" FOREIGN KEY (article_fk) REFERENCES article(id);


--
-- TOC entry 1921 (class 2606 OID 16584)
-- Name: article_tag_tagId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article_tag
    ADD CONSTRAINT "article_tag_tagId_fkey" FOREIGN KEY (tag_fk) REFERENCES tag(id);


--
-- TOC entry 1922 (class 2606 OID 16589)
-- Name: folder_parentId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY folder
    ADD CONSTRAINT "folder_parentId_fkey" FOREIGN KEY (parent_fk) REFERENCES folder(id);


--
-- TOC entry 1923 (class 2606 OID 16594)
-- Name: folder_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY folder
    ADD CONSTRAINT "folder_userId_fkey" FOREIGN KEY (client_fk) REFERENCES client(id);


-- Completed on 2015-08-18 09:16:22

--
-- PostgreSQL database dump complete
--

