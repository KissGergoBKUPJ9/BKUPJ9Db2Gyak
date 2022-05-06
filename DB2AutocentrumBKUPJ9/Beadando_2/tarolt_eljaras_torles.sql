create or replace procedure vasarloDel(adosz long) as
begin
delete from vasarlo where adosz=adoszam;
end;