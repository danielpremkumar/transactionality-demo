drop table USER if exists;

create table USER (ID serial, Name VARCHAR(5), Dept VARCHAR(10) NOT NULL, Salary bigint);