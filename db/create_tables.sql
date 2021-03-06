CREATE TABLE RATES (
  id BIGINT generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
  rate INTEGER
);

CREATE TABLE USERS (
  id BIGINT generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
  username VARCHAR(64),
  password VARCHAR(128),
  email VARCHAR(32),
  is_active boolean
);

CREATE TABLE SECURITY_ROLES (
  id BIGINT generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
  user_id INTEGER,
  role_name VARCHAR(16)
);

CREATE TABLE QUOTE(
  id BIGINT generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
  text VARCHAR(4096) NOT NULL,
  creation_date TIMESTAMP NOT NULL,
  rating BIGINT,
  user_id INTEGER,
  quote_hash VARCHAR(128) NOT NULL
);