DROP TABLE IF EXISTS review; 
DROP TABLE IF EXISTS treats; 
DROP TABLE IF EXISTS work_address; 
DROP TABLE IF EXISTS home_address; 
DROP TABLE IF EXISTS friendship; 
DROP TABLE IF EXISTS doctor_specialization; 
DROP TABLE IF EXISTS patient; 
DROP TABLE IF EXISTS doctor; 
DROP TABLE IF EXISTS administrator; 
DROP TABLE IF EXISTS account; 


CREATE TABLE Administrator (
	username varchar(16) NOT NULL UNIQUE,
	password varchar(256) NOT NULL,
	PRIMARY KEY(username)
);

CREATE TABLE Account (
	ID INTEGER NOT NULL AUTO_INCREMENT, 
	username VARCHAR(16) NOT NULL UNIQUE,
	password VARCHAR(256) NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE Doctor (
	D_ID INTEGER NOT NULL, 
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	DoB DATE NOT NULL,
	gender VARCHAR(1),
	license_year INTEGER NOT NULL,
	PRIMARY KEY (D_ID),
	FOREIGN KEY (D_ID) REFERENCES Account(ID)
);

CREATE TABLE Patient (
	P_ID INTEGER NOT NULL, 
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	email VARCHAR(64) NOT NULL,
	DoB DATE NOT NULL,
	gender VARCHAR(1),
	PRIMARY KEY (P_ID),
	FOREIGN KEY (P_ID) REFERENCES Account(ID)
);

CREATE TABLE Work_Address (
	D_ID INTEGER NOT NULL,
	province VARCHAR(2) NOT NULL,
	city VARCHAR(32) NOT NULL,
	postal_code VARCHAR(6) NOT NULL,
	street_address VARCHAR(32) NOT NULL,
	PRIMARY KEY (D_ID, province, city, postal_code, street_address),
	FOREIGN KEY (D_ID) REFERENCES Doctor(D_ID)
);

CREATE TABLE Home_Address (
	ID INTEGER NOT NULL,
	province VARCHAR(2) NOT NULL,
	city VARCHAR(32) NOT NULL,
	postal_code VARCHAR(6) NOT NULL,
	street_address VARCHAR(32) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES Account(ID)
);

CREATE TABLE Treats (
	D_ID INTEGER NOT NULL,
	P_ID INTEGER NOT NULL,
	PRIMARY KEY (D_ID, P_ID),
	FOREIGN KEY (D_ID) REFERENCES Doctor(D_ID),
	FOREIGN KEY (P_ID) REFERENCES Patient(P_ID)
);

CREATE TABLE Friendship (
	FRIENDER_ID INTEGER NOT NULL,
	FRIEND_ID INTEGER NOT NULL,
	PRIMARY KEY (FRIENDER_ID, FRIEND_ID),
	FOREIGN KEY (FRIENDER_ID) REFERENCES Patient(P_ID),
	FOREIGN KEY (FRIEND_ID) REFERENCES Patient(P_ID)
);

CREATE TABLE Review (
	D_ID INTEGER NOT NULL,
	P_ID INTEGER NOT NULL,
	review_date TIMESTAMP NOT NULL,
	rating INTEGER NOT NULL,
	recommendation boolean NOT NULL,
	comment_text VARCHAR(255),
	PRIMARY KEY (D_ID, P_ID, review_date),
	FOREIGN KEY (D_ID) REFERENCES Doctor(D_ID),
	FOREIGN KEY (P_ID) REFERENCES Patient(P_ID)
);

CREATE TABLE Doctor_Specialization (
	D_ID INTEGER NOT NULL,
	area VARCHAR(32) NOT NULL,
	PRIMARY KEY (D_ID, area),
	FOREIGN KEY (D_ID) REFERENCES Doctor(D_ID)
);

INSERT INTO account VALUES (1,'ewtan','hue');
INSERT INTO account VALUES (2,'jlam','hue');
INSERT INTO account VALUES (3,'rfmaducd','hue');
INSERT INTO account VALUES (4,'gkalsi','hue');

INSERT INTO doctor VALUES (1, 'Edward', 'Tan', CURDATE(), 'M', '2007');
INSERT INTO doctor VALUES (2, 'Jessica', 'Lam', CURDATE(), 'F', '1995');

INSERT INTO patient VALUES (3, 'Francis', 'Maducdoc', 'ray@cist.com',CURDATE(), 'M');
INSERT INTO patient VALUES (4, 'Gurjant', 'Kalsi', 'gur@cist.com',CURDATE(), 'M');

INSERT INTO home_address VALUES (1, 'ON', 'Waterloo', 'N2L3W5', '202 Lester St');
INSERT INTO work_address VALUES (1, 'ON', 'Waterloo', 'N2L3W5', '202 Lester St');

INSERT INTO home_address VALUES (2, 'ON', 'Waterloo', 'N2L3W5', '202 Lester St');
INSERT INTO work_address VALUES (2, 'ON', 'Waterloo', 'N2L3W5', '202 Lester St');

INSERT INTO home_address VALUES (3, 'ON', 'Waterloo', 'N2L3W5', '202 Lester St');
INSERT INTO home_address VALUES (4, 'ON', 'Kitchener', 'N2L3W5', '202 Lester St');

INSERT INTO doctor_specialization VALUES (1,'allergologist');
INSERT INTO doctor_specialization VALUES (2,'pediatry');
INSERT INTO doctor_specialization VALUES (2,'obstetrician');

INSERT INTO treats VALUES (1,3);
INSERT INTO treats VALUES (2,4);

INSERT INTO friendship VALUES (3,4);
INSERT INTO friendship VALUES (4,3);

INSERT INTO review VALUES (1,3,CURDATE(),0, TRUE, 'REALLY GREAT... JK');
INSERT INTO review VALUES (2,4,CURDATE(),5, TRUE, 'I wish she could hear me better');
