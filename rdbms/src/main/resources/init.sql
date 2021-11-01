--liquibase formatted sql

--changeset esmikalauskas:init

create table account1
(
    id bigserial constraint account_pk primary key,
    amount int,
    version int
);

--changeset esmikalauskas:add

insert into account1 values
(1, 500, 1),
(2, 500, 1);



