INSERT INTO SMP.T_DEPARTMENTS(id, name, dept_number) VALUES(201, 'Mathematics', 'MATHS_01');

COMMIT;


INSERT INTO SMP.T_PERSONS(id, first_name, last_name, id_number) VALUES(201, 'Ian', 'Stewart', 'ABD-201');

INSERT INTO SMP.T_PERSONS(id, first_name, last_name, id_number) VALUES(202, 'Richard', 'Adams', 'ABD-201');

COMMIT;


INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(201, 201, 'iastewart', '22345');

INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(202, 202, 'riadams', '23345');

COMMIT;


INSERT INTO SMP.T_TEACHING_STAFF(id, teaching_staff_member_type, dept_id) VALUES(201, 'Professor', 201);

INSERT INTO SMP.T_TEACHING_STAFF(id, teaching_staff_member_type, dept_id) VALUES(202, 'Research Fellow', 201);

COMMIT;
