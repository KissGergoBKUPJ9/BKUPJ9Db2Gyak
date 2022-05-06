create or replace procedure UjBeszallito(rid integer,cegnev char, szekhely char, raktar integer, szallitas date) as
begin
insert into beszallito values(rid, cegnev, szekhely, raktar, szallitas);
end;
