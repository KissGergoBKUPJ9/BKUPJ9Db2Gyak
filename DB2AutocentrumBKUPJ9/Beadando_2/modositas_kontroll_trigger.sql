create or replace trigger modositas_kontroll
before update on beszallito for each row
declare
begin
if :new.raktarkeszlet not between 0 and 10000 then
dbms_output.put_line('Nem helyes raktárkészlet');
:new.raktarkeszlet:=:old.raktarkeszlet;
end if;
end;