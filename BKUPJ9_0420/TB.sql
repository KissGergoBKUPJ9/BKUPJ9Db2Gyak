create or replace trigger TB after insert on Vasarlo_2 for each row
begin
    insert into naplo5 values('Besz�r�s', :new.VID||'_'||:new.NEV||'_'||:new.CIM, sysdate);
end;