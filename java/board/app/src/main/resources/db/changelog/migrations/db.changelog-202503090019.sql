--liquibase formatted sql
--changeset gustavo:202501091732
--comment: set unblock_reason nullable

ALTER TABLE BLOCKS
    MODIFY COLUMN unblock_reason varchar(255) NULL;

--rollback ALTER TABLE BLOCKS MODIFY COLUMN unblock_reason varchar(255) NOT NULL;

