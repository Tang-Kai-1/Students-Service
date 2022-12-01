--liquibase formatted sql

--changeset toms:1

CREATE TABLE student
(
    id             INT AUTO_INCREMENT,
    first_name   VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    --course_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);