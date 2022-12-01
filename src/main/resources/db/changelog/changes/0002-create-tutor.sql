--liquibase formatted sql

--changeset toms:1

CREATE TABLE tutor
(
    id             INT AUTO_INCREMENT,
    first_name   VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);