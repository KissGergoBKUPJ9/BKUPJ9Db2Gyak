create or replace procedure torol(x in number) as
begin
delete from auto where kor > x;
end;