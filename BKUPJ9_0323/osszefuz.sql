DECLARE
vezeteknev VARCHAR2(10) :='Kiss ';
keresztnev VARCHAR2(10) :='Gergo';
teljes VARCHAR2(20) :=CONCAT(vezeteknev, keresztnev);

begin
        DBMS_OUTPUT.put_line(teljes);
end;