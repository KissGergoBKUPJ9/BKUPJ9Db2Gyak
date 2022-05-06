create or replace package csomag1 as
procedure UjBeszallito(rid integer,cegnev char, szekhely char, raktar integer, szallitas date);
procedure UjVasarlo(adosz long, nev char, lakhely char,szulido date);
function getBeszallito(getbid in integer) return varchar;
function GetAvgRaktarkeszlet(bid int) return char;
end;