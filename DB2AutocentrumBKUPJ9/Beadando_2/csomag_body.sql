create or replace package body csomag1 as
procedure UjBeszallito(rid integer,cegnev char, szekhely char, raktar integer, szallitas date) as
begin
insert into beszallito values(rid, cegnev, szekhely, raktar, szallitas);
end;
procedure UjVasarlo(adosz long, nev char, lakhely char,szulido date) as
perror exception;
begin
if adoszam = 0 then
raise perror;
else
insert into vasarlo values(adosz, nev, lakhely,szulido);
end if;
exception
when perror then
dbms_output.put_line('Az adószám nem lehet 0');
end;
function getBeszallito(getbid in integer) return varchar as
x beszallito.cegnev%type;
y integer;
begin
select count(*) into y from beszallito where getbid=ID;
if(y<1) then
x:='Nem létezõ kód';
else
select cegnev into x from beszallito where getbid = ID;
end if;
return x;
end;
function GetAvgRaktarkeszlet(bid int) return char as
x integer;
y integer;
vi char(100):= 'Nem létezik ilyen beszallito';
begin
select count(*) into y from beszallito where bid = id;
if y>=1 then
select avg(raktarkeszlet) into x from beszallito where bid = id;
vi:='Az átlag raktárkészlet: '||x;
end if;
return vi;
end;