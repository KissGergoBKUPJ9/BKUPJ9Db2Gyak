create or replace procedure UjBeszallito(bid integer, cegnev char, szekhely char, raktarkeszlet integer, szallitas date ) as
perror exception;
begin
if raktarkeszlet >= 1000 then
raise perror;
else
insert into beszallito values(bid, cegnev, szekhely, raktarkeszlet, szallitas);
end if;
exception
when perror then
dbms_output.put_line('A raktarkeszlet túl nagy');
end;