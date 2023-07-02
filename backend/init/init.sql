CREATE TABLE SUBJECT (
   ID VARCHAR(255) UNIQUE,
   NAME VARCHAR(255) NOT NULL,
   SEMESTER SMALLINT NOT NULL,
   CONSTRAINT PK_SUBJECT PRIMARY KEY (ID)
);

CREATE TABLE PROFESSOR (
    ID VARCHAR(255) UNIQUE,
    NAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL UNIQUE,
    ROLE SMALLINT,
    CONSTRAINT PK_PROFESSOR PRIMARY KEY (ID)
);
CREATE TABLE COURSE (
    ID VARCHAR(255) UNIQUE,
    YEAR VARCHAR(4) NOT NULL,
    SUBJECT_ID VARCHAR(255) NOT NULL,
    CONSTRAINT PK_COURSE PRIMARY KEY (ID),
    CONSTRAINT FK_SUBJECT FOREIGN KEY (SUBJECT_ID) REFERENCES SUBJECT(ID)
);

CREATE TABLE STUDENT (
    INDEX VARCHAR(100) NOT NULL UNIQUE,
    EMAIL VARCHAR(255) NOT NULL UNIQUE ,
    CONSTRAINT PK_STUDENT PRIMARY KEY (INDEX)
);

CREATE TABLE SESSION (
    NAME VARCHAR(255) NOT NULL UNIQUE ,
    DUE_DATE DATE NOT NULL,
    CONSTRAINT PK_SESSION PRIMARY KEY (NAME)
);

CREATE TABLE RESULTS (
    ID BIGSERIAL UNIQUE,
    COURSE_ID VARCHAR(255) NOT NULL,
    SESSION_NAME VARCHAR(255) NOT NULL,
    PDF BYTEA,
    CONSTRAINT PK_RESULTS PRIMARY KEY (ID)
);

ALTER TABLE results ALTER COLUMN pdf TYPE bytea USING pdf::bytea;

CREATE TABLE PROFESSOR_COURSE (
    ID BIGSERIAL UNIQUE,
    PROFESSOR_ID VARCHAR(255) NOT NULL,
    COURSE_ID VARCHAR(255) NOT NULL,
    CONSTRAINT PK_PROFESSOR_SUBJECT PRIMARY KEY (ID),
    CONSTRAINT FK_COURSE FOREIGN KEY (COURSE_ID) REFERENCES COURSE(ID),
    CONSTRAINT FK_PROFESSOR FOREIGN KEY (PROFESSOR_ID) REFERENCES PROFESSOR(ID)
);

CREATE TABLE STUDENT_COURSE (
    ID BIGSERIAL UNIQUE,
    STUDENT_INDEX VARCHAR(255) NOT NULL,
    COURSE_ID VARCHAR(255) NOT NULL,
    CONSTRAINT PK_STUDENT_SUBJECT PRIMARY KEY (ID),
    CONSTRAINT FK_COURSE FOREIGN KEY (COURSE_ID) REFERENCES COURSE(ID),
    CONSTRAINT FK_STUDENT FOREIGN KEY (STUDENT_INDEX) REFERENCES STUDENT(INDEX)
);

ALTER TABLE RESULTS
    ADD CONSTRAINT FK_COURSE FOREIGN KEY (COURSE_ID) REFERENCES COURSE(ID),
    ADD CONSTRAINT FK_SESSION FOREIGN KEY (SESSION_NAME) REFERENCES SESSION(NAME);

CREATE SEQUENCE results_sequence
    INCREMENT BY 1
    START 1;
