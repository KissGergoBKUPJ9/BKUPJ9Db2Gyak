declare a auto.rsz%type := 'abc124';
b auto.tipus%type:= 'fiat' ;
c auto.szin%type := 'piros';
d auto.kor%type := 3;
e auto.ar%type := 18000000;
begin
insert into auto values (a, b, c, d, e);
end;