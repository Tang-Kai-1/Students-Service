--liquibase formatted sql

--changeset toms:1

CREATE TABLE student_course_list
(
    course_id   VARCHAR(255) NOT NULL,
    student_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (course_name),
    FOREIGN KEY (student_id) REFERENCES student (id)
    --CONSTRAINT course_id_fk
    --CONSTRAINT student_id_fk
);