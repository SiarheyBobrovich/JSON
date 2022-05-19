-- Schema

CREATE SCHEMA IF NOT EXISTS courses
    AUTHORIZATION postgres;

COMMENT ON SCHEMA courses
    IS 'Groups and students dataBase';

-- Table: courses.groupStudentsList
CREATE TABLE courses.Groups (
    id BIGSERIAL NOT NULL,
    name VARCHAR PRIMARY KEY NOT NULL
);

CREATE TABLE courses.Students (
    id BIGSERIAL PRIMARY KEY  NOT NULL,
    name VARCHAR NOT NULL,
    age SMALLINT NOT NULL,
    score DOUBLE PRECISION,
    olimpic_gamer BOOLEAN
);

CREATE TABLE courses.Students_in_groupe (
    groupe_name VARCHAR NOT NULL,
    student_id BIGINT NOT NULL UNIQUE
);

ALTER TABLE
    courses.Students_in_groupe
    ADD CONSTRAINT
        Students_in_groupe_groupe_name_Groups_name
    FOREIGN KEY
        (groupe_name)
    REFERENCES
        courses.Groups(name) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE
    courses.Students_in_groupe
    ADD CONSTRAINT
        Students_in_groupe_student_id_Students_id
    FOREIGN KEY
        (student_id)
    REFERENCES
        courses.Students(id)
        ON DELETE CASCADE ON UPDATE CASCADE;
