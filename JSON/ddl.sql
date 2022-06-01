CREATE SCHEMA IF NOT EXISTS courses AUTHORIZATION postgres;

COMMENT ON SCHEMA courses IS 'Groups and students dataBase';

CREATE TABLE courses.groups
  (
     id   BIGSERIAL NOT NULL UNIQUE,
     name VARCHAR(255) NOT NULL UNIQUE,
     PRIMARY KEY (id, name)
  );

CREATE TABLE courses.students
  (
     id            BIGSERIAL PRIMARY KEY NOT NULL,
     name          VARCHAR(255) NOT NULL,
     age           INT NOT NULL,
     score         DOUBLE PRECISION,
     olympic_gamer BOOLEAN
  );

CREATE TABLE courses.students_in_groups
  (
     group_id BIGINT NOT NULL,
     student_id BIGINT NOT NULL UNIQUE
  );

ALTER TABLE courses.students_in_groups
  ADD CONSTRAINT students_in_groups_group_name_groups_name FOREIGN KEY (
  group_id) REFERENCES courses.groups(id);
ALTER TABLE courses.students_in_groups
  ADD CONSTRAINT students_in_groups_student_id_students_id FOREIGN KEY (
  student_id) REFERENCES courses.students(id);