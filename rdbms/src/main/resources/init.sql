--liquibase formatted sql

--changeset esmikalauskas:init

create table account
(
    id long,
    amount long,
    version long
);



