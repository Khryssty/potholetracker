BEGIN TRANSACTION;

DROP TABLE IF EXISTS role, users, status, severity, location, pothole;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varChar(16) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
-- 	CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE TABLE location
(
	location_id SERIAL,
	street_address VARCHAR(128),
	street VARCHAR(64),
	city VARCHAR(32),
	state VARCHAR(32),
	postalCode VARCHAR(32),
	lat DECIMAL NOT NULL,
	long DECIMAL NOT NULL,

	CONSTRAINT pk_location_id PRIMARY KEY (location_id)
);

CREATE TABLE status
(
	status_id SERIAL,
	status VARCHAR(16) NOT NULL,

	CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);
INSERT INTO status (status) VALUES ('reported');
INSERT INTO status (status) VALUES ('reviewed');
INSERT INTO status (status) VALUES ('inspected');
INSERT INTO status (status) VALUES ('repaired');
INSERT INTO status (status) VALUES ('deleted');

CREATE TABLE severity
(
	severity_id SERIAL,
	severity VARCHAR(16) NOT NULL,

	CONSTRAINT pk_severity_id PRIMARY KEY (severity_id)
);
INSERT INTO severity (severity) VALUES ('none');
INSERT INTO severity (severity) VALUES ('low');
INSERT INTO severity (severity) VALUES ('medium');
INSERT INTO severity (severity) VALUES ('high');

CREATE TABLE pothole
(
	pothole_id SERIAL,
	location_id INT NOT NULL,
	photo VARCHAR(128) NULL, --Maybe default to a stock photo
	severity_id INT DEFAULT 1, -- id of 1 = 'none'
	status_id INT DEFAULT 1, -- id of 1 = 'reported'

	CONSTRAINT pk_pothole_id PRIMARY KEY (pothole_id),
	CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES location(location_id),
	CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES status(status_id),
	CONSTRAINT fk_severity_id FOREIGN KEY (severity_id) REFERENCES severity(severity_id)
);

CREATE TABLE log
(
	log_id SERIAL,
	pothole_id INT NOT NULL,
	modified_by INT NOT NULL,
	date_modified DATE NOT NULL,
	value_before_mod INT NOT NULL,
	value_after_mod INT NOT NULL,
	field_modified VARCHAR(100) NOT NULL,

	CONSTRAINT pk_log_id PRIMARY KEY (log_id),
	CONSTRAINT fk_pothole_id FOREIGN KEY (pothole_id) REFERENCES pothole(pothole_id),
	CONSTRAINT fk_modified_by FOREIGN KEY (modified_by) REFERENCES users(user_id),
	CONSTRAINT fk_value_before_mod FOREIGN KEY (value_before_mod) REFERENCES status(status_id),
	CONSTRAINT fk_value_after_mod FOREIGN KEY (value_after_mod) REFERENCES status(status_id)
);

COMMIT TRANSACTION;
