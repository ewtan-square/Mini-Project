SELECT d.first_name, d.last_name, d.specialization, AVG(rating), d.gender, d.license_year
FROM doctors d join reviews using (D_ID)
WHERE postal_code like 'N2L%%%' 
AND specialization = 'pediatry' 
AND D_ID in (SELECT DISTINCT D_ID FROM review
            WHERE recommendation = TRUE AND P_ID in (SELECT FRIEND_ID FROM friendship WHERE FRIENDER_ID = 3))
GROUP BY D_ID;

SELECT d.first_name, d.last_name, area, AVG(rating), d.gender, d.license_year
FROM doctors d join reviews using (D_ID)
WHERE d.gender = 'F' AND YEAR(CURDATE())-d.license_year >= 10 AND specialization = 'obstetrician'
GROUP BY D_ID;

SELECT d.first_name, d.last_name, area, AVG(rating), d.gender, d.license_year
FROM doctors d join reviews using (D_ID)
WHERE d.last_name = 'Aikenhead' AND specialization = 'allergologist'
GROUP BY D_ID;

SELECT d.first_name, d.last_name, area, AVG(rating) as average_rating, d.gender, d.license_year
FROM doctors d join reviews using (D_ID)
WHERE d.province = 'ON'
GROUP BY D_ID
HAVING AVG(rating) >= 2.5;