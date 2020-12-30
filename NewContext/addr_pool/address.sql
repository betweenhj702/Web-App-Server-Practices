create user servlet identified by java;
grant resource, connect to servlet;
conn servlet/java;


drop table ADDRESS;
drop sequence ADDRESS_SEQ;

create table ADDRESS(
   SEQ number constraint ADDRESS_PK primary key, 
   NAME varchar2(10), 
   ADDR varchar2(20), 
   RDATE date
); 
create sequence ADDRESS_SEQ increment by 1 start with 1 nocache;

insert into ADDRESS values(ADDRESS_SEQ.nextval, '안정은', '서울시', SYSDATE);
insert into ADDRESS values(ADDRESS_SEQ.nextval, '김보라', '부산시', SYSDATE);
insert into ADDRESS values(ADDRESS_SEQ.nextval, '김성태', '인천시', SYSDATE);

commit;

select CONSTRAINT_NAME, CONSTRAINT_TYPE from user_constraints where TABLE_NAME='ADDRESS';
select * from ADDRESS;

