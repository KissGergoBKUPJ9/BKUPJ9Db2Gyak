    declare nev varchar(200) :=Upper('Kiss Gergo');
    
    begin
        DBMS_OUTPUT.put_line(nev);
    end;
    
    declare nev varchar(200) :=Lower('Kiss Gergo');
    
    begin
        DBMS_OUTPUT.put_line(nev);
    end;