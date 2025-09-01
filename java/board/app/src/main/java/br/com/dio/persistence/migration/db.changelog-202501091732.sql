--liquibase formatted sql
--changeset gustavo:202501091732
--comment: boards table create

CREATE TABLE BOARDS(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL
)ENGINE=InnoDB;

--rollback DROP TABLE BOARDS
