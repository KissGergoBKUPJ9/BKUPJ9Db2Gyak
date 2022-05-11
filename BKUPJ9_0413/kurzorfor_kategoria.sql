create or replace procedure tlpfor as
    cursor cur is select * from kategoria;
begin
    for cv in cur
    loop
        dbms_output.put_line('Név: '||cv.nev);
        dbms_output.put_line('Feldolgozva: '||cur%rowcount);
    end loop;
end;