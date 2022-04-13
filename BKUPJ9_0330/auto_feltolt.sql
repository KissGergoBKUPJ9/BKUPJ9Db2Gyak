declare
x auto.ar%type;
begin
select avg(ar) into x from auto;
dbms_output.put_line(x);
end;