declare
x int;
begin
select count(*) into x from auto;
dbms_output.put_line('Aut�k sz�ma: '||x); 
end;