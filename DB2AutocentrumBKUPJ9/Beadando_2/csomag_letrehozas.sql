create or replace package csomag2 as
procedure UjBeszallito(rid integer,cegnev char, szekhely char, raktar integer, szallitas date);
procedure UjVasarlo(adosz number, nev char, lakhely char,szulido date, autorsz char);
function getBeszallito(getbid in integer) return varchar;
function GetAvgRaktarkeszlet(bid int) return char;
end;