-- Schema

CREATE SCHEMA IF NOT EXISTS courses
    AUTHORIZATION postgres;

COMMENT ON SCHEMA courses
    IS 'Groups and students dataBase';

CREATE TABLE courses.Groups (
id BIGSERIAL  NOT NULL,
name VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (id,name));

CREATE TABLE courses.Students (
id BIGSERIAL PRIMARY KEY  NOT NULL,
name VARCHAR(255) NOT NULL,
age INT NOT NULL,
score DOUBLE PRECISION,
olympic_gamer BOOLEAN);

CREATE TABLE courses.Students_in_groups (
group_name VARCHAR(255) NOT NULL,
student_id BIGINT NOT NULL UNIQUE);

ALTER TABLE courses.Students_in_groups ADD CONSTRAINT Students_in_groups_group_name_Groups_name FOREIGN KEY (group_name) REFERENCES courses.Groups(name) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE courses.Students_in_groups ADD CONSTRAINT Students_in_groups_student_id_Students_id FOREIGN KEY (student_id) REFERENCES courses.Students(id) ON DELETE CASCADE ON UPDATE CASCADE;
