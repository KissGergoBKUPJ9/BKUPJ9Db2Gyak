create or replace function GetAvgRaktarkeszlet(bid int) return char as
x integer;
y integer;
vi char(100):= 'Nem l�tezik ilyen beszallito';
begin
select count(*) into y from beszallito where bid = id;
if y>=1 then
select avg(raktarkeszlet) into x from beszallito where bid = id;
vi:='Az �tlag rakt�rk�szlet: '||x;
end if;
return vi;
end;