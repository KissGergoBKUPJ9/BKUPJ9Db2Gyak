create or replace procedure autoModositas(aid char, ujszin char) as
begin
update auto set szin = ujszin where aid = rsz;
end;