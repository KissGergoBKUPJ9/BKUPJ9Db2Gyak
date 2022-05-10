create sequence seq2
  START WITH 10
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999;
create or replace trigger autokulcs before insert on beszallito for each row
begin
:new.id := seq2.nextval;
end;