DROP TABLE servicer_specialties IF EXISTS;
DROP TABLE servicers IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE devices IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE clients IF EXISTS;


CREATE TABLE servicers (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX servicers_last_name ON servicers (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE servicer_specialties (
  servicer_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE servicer_specialties ADD CONSTRAINT fk_servicer_specialties_servicers FOREIGN KEY (servicer_id) REFERENCES servicers (id);
ALTER TABLE servicer_specialties ADD CONSTRAINT fk_servicer_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE clients (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX clients_last_name ON clients (last_name);

CREATE TABLE devices (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  bought_date DATE,
  type_id    INTEGER NOT NULL,
  client_id   INTEGER NOT NULL
);
ALTER TABLE devices ADD CONSTRAINT fk_devices_clients FOREIGN KEY (client_id) REFERENCES clients (id);
ALTER TABLE devices ADD CONSTRAINT fk_devices_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX devices_name ON devices (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  device_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_devices FOREIGN KEY (device_id) REFERENCES devices (id);
CREATE INDEX visits_device_id ON visits (device_id);