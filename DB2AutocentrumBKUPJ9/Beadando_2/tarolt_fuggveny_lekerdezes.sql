create or replace function getBeszallito(getbid in integer) return varchar as
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