declare
x auto.kor%type := 7;
begin
delete from auto where kor = x;
end;