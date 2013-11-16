CREATE VIEW patients AS
SELECT P_ID, username, first_name, last_name, gender, DoB, email, province, sity, postal_code, street_address
FROM Patient 
INNER JOIN account a ON P_ID = ID
INNER JOIN home_address h ON h.ID = a.ID;

CREATE VIEW pubv_patients AS
SELECT username, email
FROM patients;

CREATE VIEW doctors AS 
SELECT d.D_ID, username, d.first_name, d.last_name, d.gender, d.DoB, d.license_year, s.area as specialization , w.province, w.city, w.postal_code, w.street_address, h.province as home_province, h.city as home_city, h.postal_code as home_postal_code, h.street_address as home_street_address
FROM account
INNER JOIN doctor d ON ID = d.D_ID
INNER JOIN work_address w ON d.D_ID = w.D_ID
INNER JOIN home_address h ON d.D_ID = h.ID
INNER JOIN doctor_specialization s ON d.D_ID = s.D_ID; 

CREATE VIEW pubv_doctors AS
SELECT username, first_name, last_name, gender, DoB, license_year, specialization , province, city, postal_code, street_address
FROM doctors;

CREATE VIEW reviews AS
SELECT p.P_ID, a.username, p.DoB, p.email, d.D_ID, d.first_name, d.last_name, d.gender, r.area, d.license_year, r.rating, r.recommendation, r.comment_text, r.review_date
FROM patient p
INNER JOIN review r ON p.P_ID = r.P_ID
INNER JOIN doctor d ON r.D_ID = D.D_ID
INNER JOIN account a ON p.P_ID = a.ID;

CREATE VIEW pubv_reviews AS
SELECT username as patient_username, first_name as D_first_name, last_name as D_last_name, gender as D_gender, area, rating, recommendation, comment_text, review_date
FROM reviews;

CREATE VIEW all_area_specializations AS
SELECT DISTINCT area
FROM doctor_specialization;

CREATE VIEW users_w_ID AS
(SELECT D_ID as ID, username, first_name, last_name, DoB, gender FROM doctors) union (SELECT P_ID as ID, username, first_name, last_name, DoB, gender FROM patients);