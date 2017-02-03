INSERT INTO SMP.T_DEPARTMENTS(id, name, dept_number) VALUES(102, 'Fashion And Design', 'FASH_DES_01');

INSERT INTO SMP.T_DEPARTMENTS(id, name, dept_number) VALUES(103, 'Computer Science', 'COMP_SCI_02');

COMMIT;


INSERT INTO SMP.T_PERSONS(id, first_name, last_name, gender, id_number) VALUES(101, 'Tony', 'Golding', 'MALE', 'ABD-123');

INSERT INTO SMP.T_PERSONS(id, first_name, last_name, gender, id_number) VALUES(102, 'Michael', 'Danque', 'MALE', 'DFD-124');

INSERT INTO SMP.T_PERSONS(id, first_name, last_name, gender, id_number) VALUES(103, 'Roger', 'Johnson', 'MALE', 'KOI-125');

INSERT INTO SMP.T_PERSONS(id, first_name, last_name, gender, id_number) VALUES(104, 'Vivenne', 'Westwood', 'FEMALE', 'DJK-126');

COMMIT;


INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(101, 101, 'tpgolding', '12345');

INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(102, 102, 'midanque', '12346');

INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(103, 103, 'rojohnson', '12347');

INSERT INTO SMP.T_USERS_DETAILS(id, person_id, user_name, secure_token) VALUES(104, 104, 'viwestwood', '12348');

COMMIT;


INSERT INTO SMP.T_TEACHING_STAFF(id, teaching_staff_member_type, dept_id) VALUES(104, 'Affiliate Lecturer', 102);

COMMIT;

