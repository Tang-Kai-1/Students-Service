--liquibase formatted sql

--changeset toms:1

CREATE TABLE course
(
    course_name   VARCHAR(255) NOT NULL,
    tutor_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (course_name)
);