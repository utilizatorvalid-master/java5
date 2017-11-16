DROP TABLE IF EXISTS Assignments;
DROP TABLE IF EXISTS Students_Projects;
DROP TABLE IF EXISTS Projects_Skills;
DROP TABLE IF EXISTS Students_Skills;
DROP TABLE IF EXISTS Skills;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Users;

CREATE TABLE Skills (

  id   SERIAL NOT NULL  PRIMARY KEY,
  name VARCHAR(255)           NOT NULL
);

CREATE TABLE Students (
  id    SERIAL NOT NULL PRIMARY KEY,
  name  VARCHAR(255)           NOT NULL,
  email VARCHAR(255)           NOT NULL
);


CREATE TABLE Students_Skills
(
  student_id INTEGER REFERENCES students (id),
  skill_id   INTEGER REFERENCES Skills (id),
  CONSTRAINT Students_Skills_pk PRIMARY KEY (student_id, skill_id)
);

CREATE TABLE Projects (
  id          SERIAL NOT NULL PRIMARY KEY,
  name        VARCHAR(255)           NOT NULL,
  description VARCHAR(255)           NOT NULL,
  capacity    INTEGER                NOT NULL
);

CREATE TABLE Projects_Skills (
  project_id           INTEGER REFERENCES Projects (id),
  skill_id             INTEGER REFERENCES Skills (id),
  level_of_preferences INTEGER NOT NULL,
  CONSTRAINT Projects_Skills_PK PRIMARY KEY (project_id, skill_id)
);

CREATE TABLE Students_Projects (
  student_id           INTEGER  REFERENCES Students (id),
  project_id           INTEGER REFERENCES Projects (id),
  level_of_preferences INTEGER NOT NULL,
  CONSTRAINT Students_Projects_PK PRIMARY KEY (student_id, project_id)
);

CREATE TABLE Assignments (
  student_id INTEGER REFERENCES Students (id),
  project_id INTEGER REFERENCES Projects (id),
  CONSTRAINT Assignments_PK PRIMARY KEY (student_id, project_id)
);

CREATE TABLE Users(
uid SERIAL,
uname VARCHAR(60) NOT NULL,
password VARCHAR(60) NOT NULL,
PRIMARY KEY(uid));

