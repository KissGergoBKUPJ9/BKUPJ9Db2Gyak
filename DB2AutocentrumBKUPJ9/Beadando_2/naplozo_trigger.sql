create or replace trigger modositas_naplozo
after update on beszallito for each row
begin
if :old.id != :new.id then
insert into naplo values(sysdate, :old.id, 'mn@'||:new.cegnev||'-'||:old.cegnev);
elsif :old.cegnev != :new.cegnev then
insert into naplo values(sysdate, :old.id, 'mn@'||:new.cegnev||'-'||:old.cegnev);
elsif :old.szekhely != :new.szekhely then
insert into naplo values(sysdate, :old.id, 'mn@'||:new.cegnev||'-'||:old.cegnev);
elsif :old.raktarkeszlet != :new.raktarkeszlet then
insert into naplo values(sysdate, :old.id, 'mn@'||:new.cegnev||'-'||:old.cegnev);
elsif :old.szallitas != :new.szallitas then
insert into naplo values(sysdate, :old.id, 'mn@'||:new.cegnev||'-'||:old.cegnev);
end if;
end;