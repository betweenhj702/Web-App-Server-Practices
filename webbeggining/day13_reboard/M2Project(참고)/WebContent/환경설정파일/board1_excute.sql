CREATE TABLE JSP_BOARD1(
  IDX NUMBER PRIMARY KEY,
  WRITER VARCHAR2(30),
  EMAIL VARCHAR2(50),
  HOMEPAGE VARCHAR2(100),
  PWD VARCHAR2(30),
  SUBJECT VARCHAR2(200),
  CONTENT VARCHAR2(2000),
  WRITEDATE DATE,
  READNUM NUMBER,
  FILENAME VARCHAR2(200),
  FILESIZE NUMBER,
  REFER NUMBER,
  LEV NUMBER,
  SUNBUN NUMBER
);

CREATE SEQUENCE JSP_BOARD1_IDX
INCREMENT BY 1
START WITH 1
nocache;


CREATE TABLE REPLY1(
  IDX_REPLY NUMBER PRIMARY KEY, 
  WRITER VARCHAR2(30),
  PWD VARCHAR2(30),
  CONTENT VARCHAR2(1000),
  WRITEDATE DATE,
  REPLY_ID_FK REFERENCES JSP_MEMBER(ID),
  REPLY_IDX_FK REFERENCES JSP_BOARD1(IDX)
);

CREATE SEQUENCE REPLY1_IDX_REPLY
INCREMENT BY 1
START WITH 1
nocache;
