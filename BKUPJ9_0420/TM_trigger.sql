create or replace trigger TM after update on Vasarlo_2 for each row
begin
    insert into naplo5 values('M�dos�t�s', :new.VID||'_'||:new.NEV||'_'||:new.CIM||'+'||VID||'_'||NEV||'_'||CIM, sysdate);
end;